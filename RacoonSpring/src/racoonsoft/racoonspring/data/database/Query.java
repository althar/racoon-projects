package racoonsoft.racoonspring.data.database;

import java.sql.*;

public class Query
{
    protected PreparedStatement statement;
    protected Connection conn;

    public Query setParameter(int index,Object value) throws Exception
    {
        statement.setObject(index,value);
        return this;
    }

    public ResultSet executeGet(String expression) throws SQLException
    {
        return conn.createStatement().executeQuery(expression);
    }

}
