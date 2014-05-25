package racoonsoft.racoonspring.helper;

import com.sun.net.httpserver.HttpExchange;

import java.io.*;
import java.lang.reflect.Field;
import java.math.BigInteger;
import java.net.*;
import java.nio.ByteBuffer;
import java.util.*;

public class Helper
{

    public static boolean isUnix() {

        String OS = System.getProperty("os.name").toLowerCase();
        return (OS.indexOf("nix") >= 0 || OS.indexOf("nux") >= 0 || OS.indexOf("aix") > 0 );

    }
    public static <T extends Object> T clone(Cloneable<T> baseObject) throws Exception
    {
        T resultObject = baseObject.createInstance();
        Field[] fields = baseObject.getClass().getFields();
        for(int i=0; i<fields.length; i++)
        {
            fields[i].setAccessible(true);
            Field f = resultObject.getClass().getField(fields[i].getName());
            f.setAccessible(true);

            if(fields[i].get(baseObject)!=null)
            {
                f.set(resultObject,fields[i].get(baseObject));
            }
        }
        return resultObject;
    }
    public String newLine = System.getProperty("line.separator");
    public static byte[] intToBytes(int value)
    {
        byte[] bytes = ByteBuffer.allocate(4).putInt(value).array();
        return bytes;
    }
    public static byte[] longToBytes(long value)
    {
        byte[] bytes = ByteBuffer.allocate(8).putLong(value).array();
        return bytes;
    }
    public static int unsignedByteToInt(byte b)
    {
        return (int) b & 0xFF;
    }

    public static int bytesToInt(byte[] buff)
    {
        byte[] value = new byte[4];
        System.arraycopy(buff,0,value,value.length-buff.length,buff.length);
        return ByteBuffer.wrap(value).asIntBuffer().get();
    }
    public static long bytesToLong(byte[] buff)
    {
        byte[] value = new byte[8];
        System.arraycopy(buff,0,value,value.length-buff.length,buff.length);
        return ByteBuffer.wrap(value).asLongBuffer().get();
    }
    public static String getStackTraceString(StackTraceElement[] els)
    {
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<els.length; i++)
        {
            builder.append(" file: '"+els[i].getFileName()+"'");
            builder.append(" class: '"+els[i].getClassName()+"'");
            builder.append(" method: '"+els[i].getMethodName()+"'");
            builder.append(" line: '"+els[i].getLineNumber()+"'");
            builder.append(" info: '"+els[i].toString()+"'");
            builder.append(System.getProperty("line.separator"));
            builder.append("^");
            builder.append(System.getProperty("line.separator"));
        }
        return builder.toString();
    }
    public static String getStackTraceString(Exception ex)
    {
        return ex.toString()+getStackTraceString(ex .getStackTrace());
    }
    public static void sendSMS(String to, String text, String login, String id, String password)
    {
        try
        {
            URLConnection conn = (new URL("http://api.clickatell.com/http/sendmsg?api_id="+id+"&user="+login+"&password="+password+"&to="+to+"&text="+URLEncoder.encode(text,"UTF-8"))).openConnection();
            conn.connect();
            InputStream is=conn.getInputStream();
            FileOutputStream fos=new FileOutputStream("zms.lic");
            String s=new String();
            while (is.available()>0)
            {
                    byte b[]=new byte[1000];
                    int l=is.read(b);
                    s+=new String(b,0,l,"UTF-8");
            }
            fos.close();
        }
        catch (IOException e)
        {
            System.err.print(e.getMessage()+"\n");
        }
    }

    public static byte[] concat(byte[] A, byte[] B)
    {
       byte[] C= new byte[A.length+B.length];
       System.arraycopy(A, 0, C, 0, A.length);
       System.arraycopy(B, 0, C, A.length, B.length);

       return C;
    }
    
    public static HashMap<String,Object> getParameters(HttpExchange exchange) throws IOException
    {
        if ("post".equalsIgnoreCase(exchange.getRequestMethod()))
        {
            return parsePostParameters(exchange);
        }
        if ("get".equalsIgnoreCase(exchange.getRequestMethod()))
        {
            return parseGetParameters(exchange);
        }
        return null;
    }
    private static HashMap<String,Object> parseGetParameters(HttpExchange exchange)
        throws UnsupportedEncodingException
    {

        HashMap<String, Object> parameters = new HashMap<String, Object>();
        URI requestedUri = exchange.getRequestURI();
        String query = requestedUri.getRawQuery();
        parseQuery(query, parameters);
        return parameters;
    }

    private static HashMap<String,Object> parsePostParameters(HttpExchange exchange)
        throws IOException
    {
        @SuppressWarnings("unchecked")
        HashMap<String, Object> parameters =
            (HashMap<String, Object>)exchange.getAttribute("parameters");
        InputStreamReader isr =
            new InputStreamReader(exchange.getRequestBody(),"utf-8");
        BufferedReader br = new BufferedReader(isr);
        String query = br.readLine();
        parseQuery(query, parameters);
        return parameters;
    }

    private static void parseQuery(String query, Map<String, Object> parameters)
         throws UnsupportedEncodingException {

         if (query != null) {
             String pairs[] = query.split("[&]");

             for (String pair : pairs) {
                 String param[] = pair.split("[=]");

                 String key = null;
                 String value = null;
                 if (param.length > 0) {
                     key = URLDecoder.decode(param[0],
                         System.getProperty("file.encoding"));
                 }

                 if (param.length > 1) {
                     value = URLDecoder.decode(param[1],
                         System.getProperty("file.encoding"));
                 }

                 if (parameters.containsKey(key)) {
                     Object obj = parameters.get(key);
                     if(obj instanceof List<?>) {
                         List<String> values = (List<String>)obj;
                         values.add(value);
                     } else if(obj instanceof String) {
                         List<String> values = new ArrayList<String>();
                         values.add((String)obj);
                         values.add(value);
                         parameters.put(key, values);
                     }
                 } else {
                     parameters.put(key, value);
                 }
             }
         }
    }

    public static String generatePassword(int near_len)
    {
        return new BigInteger(near_len*8, new Random()).toString(32);
    }
}
