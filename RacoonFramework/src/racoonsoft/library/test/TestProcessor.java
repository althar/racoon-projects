package racoonsoft.library.test;

import racoonsoft.library.database.DBProcessor;
import racoonsoft.library.http.HTTPClient;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.ozon.APIProcessor;
import racoonsoft.library.ozon.APIRequest;
import ru.smartflex.tools.dbf.DbfEngine;
import ru.smartflex.tools.dbf.DbfIterator;
import ru.smartflex.tools.dbf.DbfRecord;
import ru.smartflex.tools.dbf.test.Fp26Reader;

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
            DBProcessor proc = new DBProcessor("localhost","finance",5432,"abacus","abacus@P","org.postgresql.Driver","jdbc:postgresql:");
            proc.connect();
            DbfIterator dbfIterator = DbfEngine.getReader("C:\\Users\\AlThar\\Downloads\\DOMA.DBF",null);

            int counter = 0;
            int total = 0;
            while (dbfIterator.hasMoreRecords()) {
                DbfRecord dbfRecord = dbfIterator.nextRecord();
                String name = dbfRecord.getString("NAME");
                String unit = dbfRecord.getString("SOCR");
                String code = dbfRecord.getString("CODE");
                Integer level = 1;
                Integer code_level_1 = Integer.valueOf(code.substring(0, 2));
                Integer code_level_2 = Integer.valueOf(code.substring(2, 5));
                Integer code_level_3 = Integer.valueOf(code.substring(5, 8));
                Integer code_level_4 = Integer.valueOf(code.substring(8, 11));
                Integer code_level_5 = Integer.valueOf(code.substring(11, 15));
                Integer code_level_6 = Integer.valueOf(code.substring(15, 19));
                if(code_level_6!=0)
                {
                    level = 6;
                }
                else if(code_level_5!=0)
                {
                    level = 5;
                }
                else if(code_level_4!=0)
                {
                    level = 4;
                }
                else if(code_level_3!=0)
                {
                    level = 3;
                }
                else if(code_level_2!=0)
                {
                    level = 2;
                }
                String relevance = code.substring(11, 13);

                String zip = dbfRecord.getString("INDEX");
                String ifns = dbfRecord.getString("GNINMB");
                String uno = dbfRecord.getString("UNO");
                String okato = dbfRecord.getString("OCATD");
                //String status = dbfRecord.getString("STATUS");

                String[] houses = name.split(",");
                for(String house: houses)
                {
                    HashMap<String,Object> params = new HashMap<String, Object>();
                    params.put("name",house);
                    params.put("unit",unit);
                    params.put("code",code);
                    params.put("code_level_1",code_level_1);
                    params.put("code_level_2",code_level_2);
                    params.put("code_level_3",code_level_3);
                    params.put("code_level_4",code_level_4);
                    params.put("code_level_5",code_level_5);
                    params.put("code_level_6",code_level_6);
                    params.put("relevance",relevance);
                    params.put("zip",zip);
                    params.put("ifns",ifns);
                    params.put("uno",uno);
                    params.put("okato",okato);
                    //params.put("status",status);
                    params.put("level",level);
                    if(total>9235156)
                    {
                        proc.executeInsert("address_classifier",params);
                    }
                    counter++;
                    total++;
                    if(counter==10)
                    {
                        counter = 0;
                        System.out.println(": "+total);
                    }
                    System.out.print(".");
                }

            }
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
