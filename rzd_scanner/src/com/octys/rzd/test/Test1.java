package com.octys.rzd.test;
import com.octys.rzd.MD5;

import java.io.*;

public class Test1
{
    public static void main(String[] args)
    {
        try
        {
            File f = new File("C:\\Users\\AlThar\\Documents\\IdeaProjects\\rzd_scanner\\rzd_scanner_1.8.8.1.apk");
//            FileInputStream reader = new FileInputStream(f);
//            int aval = reader.available();
//            byte[] bs = new byte[aval];
//            reader.read(bs,0,bs.length);
            System.out.println(MD5.get(f));
        }
        catch(Exception ex)
        {

        }

    }
}
