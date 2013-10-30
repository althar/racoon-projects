package racoonsoft.library.xml;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

/**
 *
 * @author Федорович Дмитрий
 */
public class StringInputStream extends InputStream
{
    private byte[] dat;
    private int index;

    public StringInputStream(String data) throws UnsupportedEncodingException
    {
        dat = data.getBytes("UTF-8");
    }
    public int read() throws IOException
    {
        try
        {
            int val = (int)dat[index];
            index++;
            return val;
        }
        catch(Exception ex)
        {
            return -1;
        }
    }
    public String getDataString() throws UnsupportedEncodingException
    {
        return new String(dat,"UTF-8");
    }
}
