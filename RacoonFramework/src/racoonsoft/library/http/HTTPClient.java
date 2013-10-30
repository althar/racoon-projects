package racoonsoft.library.http;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.Iterator;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class HTTPClient
{
    public static String line_separator = System.getProperty("line.separator");
    public static String default_encoding = "UTF-8";
    
    public static byte[] sendHTTPRequest(byte[] pack,String url) throws IOException
    {
		URL current_url = new URL(url);
		URLConnection yc = current_url.openConnection();
		yc.setDoOutput(true);

		OutputStream out = yc.getOutputStream();
		out.write(pack);
		out.flush();

		InputStream in = yc.getInputStream();
		BufferedInputStream bin = new BufferedInputStream(in);
		byte[] bs = new byte[bin.available()];
		bin.read(bs);
		bin.close();
		return bs;
    }
    public static String sendHTTPRequest(String pack,String encoding, String url) throws IOException
    {
		URL current_url = new URL(url);
		URLConnection yc = current_url.openConnection();
		yc.setDoOutput(true);

		OutputStream out = yc.getOutputStream();
		out.write(pack.getBytes(encoding));
		out.flush();

		BufferedReader in = new BufferedReader(
							new InputStreamReader(
							yc.getInputStream(),encoding));
		StringBuilder builder = new StringBuilder();
		String inputLine;

		while ((inputLine = in.readLine()) != null)
		{
			builder.append(inputLine);
			builder.append(line_separator);
		}
		in.close();
		return builder.toString();
    }
    public static String sendHTTPRequestWithHeaders(String pack, String url,HashMap<String,String> headers) throws IOException
    {
        URL current_url = new URL(url);
        URLConnection yc = current_url.openConnection();
        if(headers!=null)
        {
            Iterator<String> names = headers.keySet().iterator();
            while(names.hasNext())
            {
                String key = names.next();
                String value = headers.get(key);
                yc.setRequestProperty(key,value);
            }
        }
        yc.setDoOutput(true);

        OutputStream out = yc.getOutputStream();
        out.write(pack.getBytes());
        out.flush();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream(),default_encoding));
        StringBuilder builder = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
        {
            builder.append(inputLine);
            builder.append(line_separator);
        }
        in.close();
        return builder.toString();
    }
    public static String sendHTTPRequest(String pack, String url) throws IOException
    {
		return sendHTTPRequestWithHeaders(pack, url, null);
    }

    public static String sendHTTPSRequestWithHeaders(String url,String method,HashMap<String,String> headers,byte[] pack) throws Exception
    {
        URL current_url = new URL(url);


        // All certs allowed...
        TrustManager[] trustAllCerts = new TrustManager[]{new X509TrustManager(){
            public X509Certificate[] getAcceptedIssuers(){return null;}
            public void checkClientTrusted(X509Certificate[] certs, String authType){}
            public void checkServerTrusted(X509Certificate[] certs, String authType){}
        }};
        SSLContext sc = SSLContext.getInstance("TLS");
        sc.init(null, trustAllCerts, new SecureRandom());
        HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());

        HttpsURLConnection yc = (HttpsURLConnection)current_url.openConnection();
        yc.setRequestMethod(method);
        yc.setRequestProperty("Content-Length", "0");

        if(headers!=null)
        {
            Iterator<String> names = headers.keySet().iterator();
            while(names.hasNext())
            {
                String key = names.next();
                String value = headers.get(key);
                yc.setRequestProperty(key,value);
            }
        }
        yc.setDoOutput(true);

        OutputStream out = yc.getOutputStream();
        out.write(pack);
        out.flush();

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream(),default_encoding));
        StringBuilder builder = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
        {
            builder.append(inputLine);
            builder.append(line_separator);
        }
        in.close();
        return builder.toString();
    }
    public static String sendHTTPSRequestWithHeaders(String url,String method,HashMap<String,String> headers) throws IOException
    {
        URL current_url = new URL(url);

        HttpsURLConnection yc = (HttpsURLConnection)current_url.openConnection();
        yc.setRequestMethod(method);
        yc.setRequestProperty("Content-Length", "0");
        if(headers!=null)
        {
            Iterator<String> names = headers.keySet().iterator();
            while(names.hasNext())
            {
                String key = names.next();
                String value = headers.get(key);
                yc.setRequestProperty(key,value);
            }
        }
        //yc.setDoOutput(true);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(
                        yc.getInputStream(),default_encoding));
        StringBuilder builder = new StringBuilder();
        String inputLine;

        while ((inputLine = in.readLine()) != null)
        {
            builder.append(inputLine);
            builder.append(line_separator);
        }
        in.close();
        return builder.toString();
    }
    public static String sendHTTPSRequest(String url,String method) throws IOException
    {
		return sendHTTPSRequestWithHeaders(url,method,null);
    }
    public static String sendHTTPRequestWithMethod(String url,String method,HashMap<String,String> headers) throws IOException
    {
        URL current_url = new URL(url);
        HttpURLConnection yc = (HttpURLConnection)current_url.openConnection();
		yc.setRequestMethod(method);
        if(headers!=null)
        {
            Iterator<String> names = headers.keySet().iterator();
            while(names.hasNext())
            {
                String key = names.next();
                String value = headers.get(key);
                yc.setRequestProperty(key,value);
            }
        }
		yc.setDoOutput(true);

		byte[] b = new byte[0];
		OutputStream out = yc.getOutputStream();
		out.write(b);
		out.flush();

		InputStream inp_stream = yc.getInputStream();
		InputStreamReader inp_stream_reader = new InputStreamReader(inp_stream,default_encoding);
		BufferedReader in = new BufferedReader(inp_stream_reader);

		StringBuilder builder = new StringBuilder();
		String inputLine;
		while ((inputLine = in.readLine()) != null)
		{
			builder.append(inputLine);
			builder.append(line_separator);
		}
		in.close();
		return builder.toString();
    }
    public static String sendHTTPRequestWithMethod(String url,String method) throws IOException
    {
        return sendHTTPRequestWithMethod(url,method,null);
    }
    public static String sendHTTPSRequestWithMethod(String url,String method) throws IOException,URISyntaxException
    {
		URI uri = new URI(url);
        URL current_url = uri.toURL();
        HttpsURLConnection yc = (HttpsURLConnection)current_url.openConnection();
		yc.setRequestMethod(method);
		if(method.equalsIgnoreCase("post"))
		{
			yc.setDoOutput(true);

			byte[] b = new byte[0];
			OutputStream out = yc.getOutputStream();
			out.write(b);
			out.flush();
		}
		InputStream inp_stream = yc.getInputStream();
		InputStreamReader inp_stream_reader = new InputStreamReader(inp_stream,default_encoding);
        BufferedReader in = new BufferedReader(inp_stream_reader);

        StringBuilder builder = new StringBuilder();
        String inputLine;
        while ((inputLine = in.readLine()) != null)
		{
            builder.append(inputLine);
            builder.append(line_separator);
		}
        in.close();
        return builder.toString();
    }
}
