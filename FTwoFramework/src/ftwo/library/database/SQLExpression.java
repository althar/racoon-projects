package ftwo.library.database;

public class SQLExpression
{
    private String value;
    public SQLExpression(String expression)
    {
        value = expression;
    }
    public String getValue()
    {
        return value;
    }
}
