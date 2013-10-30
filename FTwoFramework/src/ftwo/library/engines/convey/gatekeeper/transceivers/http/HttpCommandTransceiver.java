package ftwo.library.engines.convey.gatekeeper.transceivers.http;

import ftwo.library.collections.Queue;
import ftwo.library.engines.convey.commandstorage.Command;
import ftwo.library.engines.convey.gatekeeper.CommandTransceiver;
import ftwo.library.helper.Helper;
import ftwo.library.logging.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.HashMap;

public class HttpCommandTransceiver extends CommandTransceiver
{
    private Queue<HttpTransmission> IncomingTransmissions = new Queue<HttpTransmission>();
    private HashMap<Long,HttpTransmission> ProcessingTransmissions = new HashMap<Long,HttpTransmission>();
    private Queue<HttpTransmission> OutgoingTransmissions = new Queue<HttpTransmission>();
    private ServerSocket SSocket;

    public HttpCommandTransceiver(int port) throws IOException
    {
        super("HttpCommandTransceiver");
        SSocket = new ServerSocket(port);
        SSocket.setSoTimeout(10);
    }
    public void processTransfer(String process_name)
    {
        if(process_name.equalsIgnoreCase("receive"))
        {
            receive();
        }
        if(process_name.equalsIgnoreCase("transmit"))
        {
            transmit();
        }
        if(process_name.equalsIgnoreCase("accept"))
        {
            accept();
        }
    }
    public void accept()
    {
        try
        {
            Socket s = SSocket.accept();
            s.setSoTimeout(1);
            HttpTransmission transmission = new HttpTransmission(s);
            IncomingTransmissions.enqueue(transmission);
            Logger.info("Server socket got client connection.");
        }
        catch(SocketTimeoutException stoex)
        {
            Logger.fine("Server socket of HttpCommandTransceiver timed out.");
        }
        catch(Exception ex)
        {
            Logger.error("Server socket of HttpCommandTransceiver error: " + Helper.getStackTraceString(ex));
        }
    }
    public void receive()
    {
        try
        {
            if(IncomingTransmissions.isEmpty())
            {
                return;
            }
            HttpTransmission t = IncomingTransmissions.dequeue();
            t.process();
            if(t.status()==HttpTransmission.STATUS_CONNECTED)
            {
                IncomingTransmissions.enqueue(t);
            }
            if(t.status()==HttpTransmission.STATUS_REQUEST_EXTRACTED)// Got the request
            {
                Command c = t.getRequestCommand();
                System.out.println("Request extracted: "+c);
                ProcessingTransmissions.put(t.getSequenceID(), t);
                String queue_name = c.getStringValue("queue_name");
                if(queue_name!=null)
                {
                    System.out.println("Put in Q: "+queue_name);
                    putCommand(queue_name,c,false);
                }
                else
                {
                    System.out.println("Put in Q: http_request");
                    putCommand("http_request",c,false);
                }

            }
            if(t.status()==HttpTransmission.STATUS_CONNECTION_LOST)// So whatever
            {
                Logger.warning("Http transmission lost connection during receive circle.");
            }

            //Thread.currentThread().sleep(1000);
        }
        catch(Exception ex)
        {
            System.out.println(Helper.getStackTraceString(ex));
        }
    }
    public static long Processed = 0;
    public void transmit()
    {
        try
        {
            Command c = getCommand("http_response");
            if(c==null)
            {
                return;
            }
            HttpTransmission t = ProcessingTransmissions.get(c.getSequenceID());
            if(t==null)
            {
                Logger.warning("No transmission to send response to client. Sequence_id="+t.getSequenceID());
            }
            t.process();

            // Charge responses to transmissions
            if(t.status()==HttpTransmission.STATUS_REQUEST_EXTRACTED)// Got the request and response is ready
            {
                t.setResponseCommand(c);
                ProcessingTransmissions.remove(t.getSequenceID());
                OutgoingTransmissions.enqueue(t);
            }
            else if(t.status()==HttpTransmission.STATUS_CONNECTION_LOST)// So whatever
            {
                Logger.warning("Http transmission lost connection during transmit circle.");
                ProcessingTransmissions.remove(t.getSequenceID());
            }

            // Send responses
            if(OutgoingTransmissions.isEmpty())
            {
                return;
            }
            t = OutgoingTransmissions.dequeue();
            t.process();
            if(t.status()==HttpTransmission.STATUS_RESPONSE_EXTRACTED)// Just wait for response to be sent
            {
                OutgoingTransmissions.enqueue(t);
            }
            else if(t.status()==HttpTransmission.STATUS_RESPONSE_SENT)// Done. Response is sent
            {
                Logger.fine("Response sent to http client. " + t.getResponseCommand());
                System.out.println("Response sent to http client. " + t.getResponseCommand());
            }
            //Thread.currentThread().sleep(1000);
        }
        catch(Exception ex)
        {
            System.out.println(Helper.getStackTraceString(ex));
        }
    }
}
