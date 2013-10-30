package ftwo.library.http;

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
import java.util.HashMap;
import javax.net.ssl.HttpsURLConnection;

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
    public static String sendHTTPRequest(String pack, String url) throws IOException
    {
		URL current_url = new URL(url);
		URLConnection yc = current_url.openConnection();
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
    public static String sendHTTPSRequest(String url,String method) throws IOException
    {
		URL current_url = new URL(url);

		HttpsURLConnection yc = (HttpsURLConnection)current_url.openConnection();
		yc.setRequestMethod(method);
		yc.setRequestProperty("Content-Length", "0");
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
    public static String sendHTTPRequestWithMethod(String url,String method) throws IOException
    {
        URL current_url = new URL(url);
        HttpURLConnection yc = (HttpURLConnection)current_url.openConnection();
		yc.setRequestMethod(method);
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
        public static String sendHTTPPostRequest(String url,String body,String encoding) throws IOException
    {
        OutputStream out = null;
        InputStream in = null;
        HttpURLConnection yc = null;
        StringBuilder builder = null;
        try
        {
            byte[] buff = body.getBytes(encoding);

            URL current_url = new URL(url);
            yc = (HttpURLConnection)current_url.openConnection();
            yc.setReadTimeout(1000);
            yc.setDoOutput(true);
            yc.setRequestMethod("POST");
            yc.setRequestProperty("Content-Length", String.valueOf(buff.length));

            out = yc.getOutputStream();
            in = yc.getInputStream();

            out.write(buff);
            out.flush();

            InputStreamReader inp_stream_reader = new InputStreamReader(in,encoding);
            BufferedReader in_reader = new BufferedReader(inp_stream_reader);

            builder = new StringBuilder();
            String inputLine;
            while ((inputLine = in_reader.readLine()) != null)
            {
                builder.append(inputLine);
                builder.append(line_separator);
            }
        }
        finally
        {
            yc.disconnect();
            out.close();
            in.close();

        }
        return builder.toString();
    }
    public static String sendHTTPGetRequest(String url,String encoding) throws IOException
    {
        InputStream in = null;
        HttpURLConnection yc = null;
        StringBuilder builder = null;
        try
        {
            URL current_url = new URL(url);
            yc = (HttpURLConnection)current_url.openConnection();
            yc.setReadTimeout(1000);
            yc.setRequestMethod("GET");
            in = yc.getInputStream();

            InputStreamReader inp_stream_reader = new InputStreamReader(in,encoding);
            BufferedReader in_reader = new BufferedReader(inp_stream_reader);

            builder = new StringBuilder();
            String inputLine;
            while ((inputLine = in_reader.readLine()) != null)
            {
                builder.append(inputLine);
                builder.append(line_separator);
            }
        }
        finally
        {
            yc.disconnect();
            in.close();

        }
        return builder.toString();
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
