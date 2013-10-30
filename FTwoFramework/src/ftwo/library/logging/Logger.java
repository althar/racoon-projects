package ftwo.library.logging;

import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.SimpleFormatter;

public class Logger
{
    private static final java.util.logging.Logger BaseLogger = java.util.logging.Logger.getAnonymousLogger();
    public Logger()
    {
        BaseLogger.setUseParentHandlers(false);
    }
    public static void setUseParentHandlers(boolean use)
    {
        BaseLogger.setUseParentHandlers(use);
    }
    public static void addFileLogger(String name_pattern)
    {
        try
        {
            FileHandler handler = new FileHandler(name_pattern, 1000000, 10, true);
            handler.setFormatter(new SimpleFormatter());
            BaseLogger.addHandler(handler);
        }
        catch(Exception ex)
        {
            System.out.println("Loger creation error: "+ex.toString());
        }
    }
    public static void setLogLevel(Level level)
    {
        BaseLogger.setLevel(level);
    }
    public static void fine(String text)
    {
        BaseLogger.fine(text);
    }
    public static void info(String text)
    {
        BaseLogger.info(text);
    }
    public static void warning(String text)
    {
        BaseLogger.warning(text);
    }
    public static void error(String text)
    {
        BaseLogger.severe(text);
    }
    public static void addHandler(Handler handler)
    {
        BaseLogger.addHandler(handler);
    }
}
