package racoonsoft.library.test;

import racoonsoft.library.database.DBProcessor;
import racoonsoft.library.helper.StringHelper;
import racoonsoft.library.http.HTTPClient;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.ozon.APIProcessor;
import racoonsoft.library.ozon.APIRequest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.AWTEventListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestProcessor
{
    public static void main(String[] args)
    {
	    long start = new Date().getTime();
        try
        {
            System.out.println(StringHelper.getCharEncodedString("Тип SIM-карты: Micro-SIM\\u000d\\u000a"));
        }
        catch(Exception ex)
        {
	        System.out.println(ex.toString());
        }
        finally
        {
            System.out.println("Total time: "+(new Date().getTime()-start));
        }
    }
}
