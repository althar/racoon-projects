package ftwo.library.engines.convey.commandstorage;

import ftwo.library.database.DBRecord;
import ftwo.library.helper.Helper;
import ftwo.library.logging.Logger;
import ftwo.library.xml.XMLProcessor;

import java.util.HashMap;

public class Command extends DBRecord
{
    private static long IDSeed = 0;
    public Command()
    {
        super(new HashMap<String, Object>());
        setValue("SEQUENCE_ID",generateID());
    }
    public Command(Command command)
    {
        super(command.Fields);
    }
    public Command getResponse()
    {
        return new Command(this);
    }
    public void setResult(String result,String result_message)
    {
        try
        {
            XMLProcessor proc = new XMLProcessor();
            proc.addNode("root","result",result);
            proc.addNode("root","result_message",result_message);
            setValue("data",proc);
        }
        catch(Exception ex)
        {
            Logger.error(Helper.getStackTraceString(ex));
        }
    }
    public void setData(String path,String name, Object value)
    {
        XMLProcessor proc = (XMLProcessor)getValue("data");
        if(proc!=null)
        {
            proc.addNode(path,name,value);
        }
    }
    public String getDataValue(String path)
    {
        XMLProcessor proc = (XMLProcessor)getValue("data");
        if(proc!=null)
        {
            return proc.getValue(path);
        }
        return null;
    }
    public String getResult()
    {
        XMLProcessor proc = (XMLProcessor)getValue("data");
        return proc.getValue("root.result_message");
    }
    public String getResultMessage()
    {
        return getStringValue("result_message");
    }
    private static long generateID()
    {
        IDSeed++;
        return IDSeed;
    }
    public long getSequenceID()
    {
        return getLongValue("SEQUENCE_ID");
    }

}
