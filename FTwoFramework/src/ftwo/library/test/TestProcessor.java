package ftwo.library.test;

import ftwo.library.engines.convey.ConveyProcessor;
import ftwo.library.engines.convey.commandstorage.Command;
import ftwo.library.engines.convey.gatekeeper.transceivers.http.HttpCommandTransceiver;
import ftwo.library.engines.convey.processor.CommandProcessor;
import ftwo.library.helper.Helper;
import ftwo.library.http.HTTPClient;
import ftwo.library.logging.Logger;

import java.util.Date;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;

public class TestProcessor
{
    public static void main(String[] args)
    {
	    long start = new Date().getTime();
        ConveyProcessor convey = new ConveyProcessor();

        try
        {
            Logger.setUseParentHandlers(false);
            Logger.addHandler(new ConsoleHandler());
            Logger.setLogLevel(Level.ALL);
//            FileTransferProcessor proc = new FileTransferProcessor(8081,62222,"C:/temp","*");
//            proc.start();


            HttpCommandTransceiver trans = new HttpCommandTransceiver(8081);
            convey.addCommandTransceiver("http_transceiver",trans);
            convey.addCommandProcessor("command_processor", new CommandProcessor("http_command_processor") {
                @Override
                public void process() {
                    try
                    {
                        Command c = getCommand("q_name_1");
                        if(c!=null)
                        {
                            c.setValue("Content-Type", "text/xml");
                            c.setValue("data", "<html><head></head><body>DAAAAS</body></html>");
                            putCommand("http_response", c, false);
                        }
                    }
                    catch (Exception ex)
                    {
                        System.out.println(Helper.getStackTraceString(ex));
                    }
                }
            });
            convey.startTransceivers();
            convey.startCommandProcessors();
            for(int i=0;i<10; i++)
            {
                start = new Date().getTime();
                //String body = HTTPClient.sendHTTPPostRequest("http://localhost:8081?cmd=dede&command=comm","g","UTF-8");
                String body = HTTPClient.sendHTTPGetRequest("http://localhost:8081?cmd=dede&command=comm&queue_name=q_name_1","UTF-8");
                System.out.println("BODY: "+body+" Time: "+(new Date().getTime()-start));
                HttpCommandTransceiver.Processed++;
            }
            System.out.println("Total time: "+(new Date().getTime()-start)+"  processed: "+HttpCommandTransceiver.Processed);

//            Thread.sleep(10000000);
        }
        catch(Exception ex)
        {
	        System.out.println(ex.toString());
        }
        finally
        {
            try
            {
                convey.stopTransceivers();
                convey.stopCommandProcessors();
            }
            catch(Exception ex)
            {
                System.out.println(ex.toString());
            }
            System.out.println("Total time: "+(new Date().getTime()-start)+"  processed: "+HttpCommandTransceiver.Processed);
        }
    }
}
