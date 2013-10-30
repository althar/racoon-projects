package com.octys.rzd;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import org.apache.http.*;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.AuthState;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Communicator {
	private static final String TAG = TicketScannerService.class.getName();

	private TicketScannerApplication mApp;
	private DefaultHttpClient mHTTPclient;
//	private String mIMEI_hash;
	
	public Communicator(TicketScannerApplication app, String login, String password) {
		mApp = app;
//		mIMEI_hash = app.getIMEI_MD5();

		mHTTPclient = new DefaultHttpClient();
		final HttpParams params = mHTTPclient.getParams();
        HttpConnectionParams.setConnectionTimeout(params, Consts.HTTP_TIMEOUT);
        HttpConnectionParams.setSoTimeout(params, Consts.HTTP_TIMEOUT);
        ConnManagerParams.setTimeout(params, Consts.HTTP_TIMEOUT);
        
	    mHTTPclient.getCredentialsProvider().setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(login,password));
	    mHTTPclient.addRequestInterceptor(preemptiveAuth, 0);
	
	}
	
	public final boolean isInternetOn() {
		ConnectivityManager connec =  (ConnectivityManager)mApp.getSystemService(Context.CONNECTIVITY_SERVICE);
		return
		   connec.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
		   connec.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
		}
	
	HttpRequestInterceptor preemptiveAuth =	new HttpRequestInterceptor() {
		@Override
        public void process(final HttpRequest request, final HttpContext context) throws HttpException, IOException {
            AuthState state = (AuthState) context.getAttribute(ClientContext.TARGET_AUTH_STATE);
            if (state.getAuthScheme() == null) {
                BasicScheme scheme = new BasicScheme();
                CredentialsProvider credentialsProvider = (CredentialsProvider) context.getAttribute(ClientContext.CREDS_PROVIDER);
                Credentials credentials = credentialsProvider.getCredentials(AuthScope.ANY);
                if (credentials == null) {
                    throw new HttpException();
                }
                state.setAuthScope(AuthScope.ANY);
                state.setAuthScheme(scheme);
                state.setCredentials(credentials);
            }
		}
    };
 
	public boolean send(DatabaseHelper.Record r, String imei_hash, String fingerprint) {
		HttpResponse response = null;
		boolean result = false;
		
		try {
		    HttpPost request = new HttpPost(mApp.mCfg.CFG_PRODUCTION_SERVER_ADDRESS);
		    request.addHeader("content-type", "application/x-www-form-urlencoded");
		    List<NameValuePair> l = r.getList(imei_hash, fingerprint);
		    
		    int i=0;
		    Log.w(TAG, "request:");
		    for (NameValuePair p: l) {
		    	Log.w(TAG, "  "+i+" "+p.getName()+" "+p.getValue());
		    }
		    Log.w(TAG, "end of request.");
		    
		    request.setEntity(new UrlEncodedFormEntity(l));//, HTTP.UTF_8));
		    response = mHTTPclient.execute(request);
//		    Log.w(TAG, "response = "+getString(response));
		    StatusLine sl = response.getStatusLine();
		    result = (sl!=null && sl.getStatusCode()==HttpStatus.SC_OK);
	    } catch (ClientProtocolException e) {
	    } catch (IOException e) {
	    } catch (Exception e) {
	    	Log.w(TAG, "rzd_scanner send "+mApp.mCfg.CFG_PRODUCTION_SERVER_ADDRESS);
	    	Log.w(TAG, e.toString());
	    }

		try {
			if( response!=null && response.getEntity() != null ) 
		         response.getEntity().consumeContent();
			} catch (IOException e) {}
		
		return result;
		
	}
	
	public String getString(InputStream is) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while ((line = reader.readLine()) != null) {
				sb.append(line + mApp.NL);
			}
			is.close();
			return sb.toString();
		
		} catch(Exception e){
			Log.e(TAG, "Error converting result "+e.toString());
			return "";
		} 
	}

	public String getString(HttpResponse r) {
		try {
			String s = getString(r.getEntity().getContent());
			return s;
		} catch(Exception e){
			Log.e(TAG, "Error converting result "+e.toString());
			return "";
		} 
	}
	
	public String getString(String url){
		HttpResponse response = null;
		String s = "";
		try{
			HttpPost httppost = new HttpPost(url);
			response = mHTTPclient.execute(httppost);
			StatusLine sl = response.getStatusLine();
			if (sl!=null && sl.getStatusCode()==HttpStatus.SC_OK)
				s = getString(response);
			else
				Log.w(TAG, "HTTP request failed: "+(sl!=null ? ""+sl.getStatusCode() : "unknown status"));
		}catch(Exception e){
			Log.e(TAG, "Error in http connection "+e.toString());
		}
		try {
			if( response!=null && response.getEntity() != null ) 
		         response.getEntity().consumeContent();
			} catch (IOException e) {}
		return s;
	}	

}
