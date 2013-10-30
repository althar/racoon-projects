package racoonsoft.library.test;

import racoonsoft.library.helper.Helper;
import racoonsoft.library.processors.FileTransferProcessor;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Date;


public class Test
{
    public static void main(String[] args)
    {
	    long start = new Date().getTime();
        try
        {
            FileTransferProcessor proc = new FileTransferProcessor(61111,62222,"C:/temp",null);
            proc.start();
            Socket s = new Socket("localhost",61111);
            s.setSoTimeout(1000);
            s.setReceiveBufferSize(200000);
            s.setSendBufferSize(200000);
            InputStream in = s.getInputStream();
            OutputStream out = s.getOutputStream();


            File f = new File("C:\\Films\\[PSVita] Land of The Lost\\Land of The Lost.mp4");
            FileInputStream file = new FileInputStream(f);

            byte[] size_buff = Helper.longToBytes(f.length());

            byte[] name_buff = "NEWONE.mp4".getBytes("UTF-8");
            byte[] name_size_buff = Helper.intToBytes(name_buff.length);
            byte[] name_size_buff_2 = new byte[1];
            byte[] param_size_buff = new byte[2];
            System.arraycopy(name_size_buff,3,name_size_buff_2,0,1);

            out.write(size_buff);
            out.write(name_size_buff_2);
            out.write(name_buff);
            out.write(param_size_buff);


            byte[] id_buff = new byte[4];
            in.read(id_buff);
            int id = Helper.bytesToInt(id_buff);
            System.out.println("ID: " + id);
            long total = 0;
            int total_sent = 0;
            while(file.available()>0)
            {
                int aval = file.available();
                if(aval>200000)
                {
                    aval = 200000;
                }
                total+=aval;
                byte[] buff = new byte[aval];
                file.read(buff);
                out.write(buff);
                out.flush();

                byte[] income = new byte[9];
                byte[] income_size = new byte[8];
                while(in.available()>=9)
                {
                    income = new byte[9];
                    in.read(income);
                    System.arraycopy(income,0,income_size,0,8);
                    long already_read = Helper.bytesToLong(income_size);
                    //System.out.println("Already_saved: "+already_read);
                }
                //System.out.println("total sent: "+total);
            }
            System.out.println("Done: "+(new Date().getTime()-start));
            proc.stop();
            Thread.sleep(3000);
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
