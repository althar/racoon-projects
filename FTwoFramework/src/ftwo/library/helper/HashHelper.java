package ftwo.library.helper;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class HashHelper
{
    public static String getMD5InHexadecimalSystem(String base_string) throws NoSuchAlgorithmException,UnsupportedEncodingException
    {
        byte[] res_bytes = getMD5(base_string.getBytes("UTF-8"));
        return new String(res_bytes,"UTF-8");
    }

    public static byte[] getMD5(byte[] value) throws NoSuchAlgorithmException
    {
        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
        digest.update(value,0,value.length);
        byte[] md5hash = new byte[32];

        md5hash = digest.digest();

        return md5hash;
    }
    public static byte[] getMD5_hex(byte[] value) throws NoSuchAlgorithmException
    {
        MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
        digest.update(value,0,value.length);
        byte[] md5hash = new byte[32];

        md5hash = digest.digest();

        StringBuffer buf = new StringBuffer(32);
        for (int i = 0; i < md5hash.length; i++)
        {
            int intVal = md5hash[i] & 0xff;
            if (intVal < 0x10)
            {
                buf.append("0");
            }
            buf.append(Integer.toHexString(intVal).toUpperCase());
        }
        //System.out.println(buf.toString());
        return buf.toString().getBytes();
    }
    public static String getMD5Hex(String s,String encoding) throws UnsupportedEncodingException
    {
        String result = null;
        try
	    {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            byte[] bs = null;
            if(encoding!=null)
            {
                bs = s.getBytes(encoding);
            }
            else
            {
                bs = s.getBytes();
            }
            byte[] digest = md5.digest(bs);
            result = toHex(digest);
        }
        catch (NoSuchAlgorithmException e)
	    {

        }
        return result;
    }
    public static String getMD5Hex(String s) throws UnsupportedEncodingException
    {
        return getMD5Hex(s,null);
    }

    public static String toHex(byte[] a)
    {
        StringBuilder sb = new StringBuilder(a.length * 2);
        for (int i = 0; i < a.length; i++)
	{
            sb.append(Character.forDigit((a[i] & 0xf0) >> 4, 16));
            sb.append(Character.forDigit(a[i] & 0x0f, 16));
        }
        return sb.toString();
    }
}
