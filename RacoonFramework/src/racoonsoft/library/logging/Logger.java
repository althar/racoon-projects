package racoonsoft.library.logging;

import java.util.logging.FileHandler;
import java.util.logging.Level;

public class Logger
{
    private static final java.util.logging.Logger BaseLogger = java.util.logging.Logger.getAnonymousLogger();
    public Logger()
    {
        BaseLogger.setUseParentHandlers(false);
    }
    public void addFileLogger(String name_pattern)
    {
        try
        {
            BaseLogger.addHandler(new FileHandler(name_pattern, 10000, 10, true));
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
}
