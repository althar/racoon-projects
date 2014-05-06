package racoonsoft.racoonspring.data.database;

import racoonsoft.racoonspring.data.annotation.DataStructureFieldExclude;
import racoonsoft.racoonspring.data.annotation.DataStructureTable;
import racoonsoft.racoonspring.data.structure.DatabaseStructure;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class InsertQuery extends Query
{
    public InsertQuery(Connection c, String query) throws Exception
    {
        conn = c;
        statement = conn.prepareStatement(query);
    }
    public InsertQuery(Connection c,DatabaseStructure structure) throws Exception
    {
        this(c,structure,null);
    }
    public InsertQuery(Connection c,DatabaseStructure structure,String table) throws Exception
    {
        Field[] fs = structure.getClass().getDeclaredFields();
        if(table==null)
        {
            table = structure.getClass().getAnnotation(DataStructureTable.class).name();
        }
        if(table==null)
        {
            throw new Exception("Table name is not defined");
        }
        StringBuilder builder = new StringBuilder("INSERT INTO ");
        builder.append(table);
        builder.append(" (");
        ArrayList<Object> values = new ArrayList<Object>();
        StringBuilder valueBuilder = new StringBuilder(" VALUES (");
        for(int i=0; i<fs.length; i++)
        {
            fs[i].setAccessible(true);
            if(!fs[i].isAnnotationPresent(DataStructureFieldExclude.class))
            {
                builder.append(fs[i].getName());
                values.add(fs[i].get(structure));
                valueBuilder.append("?");
                if(i+1<fs.length)
                {
                    builder.append(",");
                    valueBuilder.append(",");
                }
            }
        }
        HashMap<String,Object> fields = structure.fields();
        Iterator<String> iter = fields.keySet().iterator();
        while(iter.hasNext())
        {
            String name = iter.next();
            Object value = fields.get(name);
            builder.append(name);
            values.add(value);
            valueBuilder.append("?");
            if(iter.hasNext())
            {
                builder.append(",");
                valueBuilder.append(",");
            }
        }
        builder.append(") ");
        valueBuilder.append(") ");
        builder.append(valueBuilder.toString());
        builder.append(" RETURNING id");
        conn = c;
        statement = conn.prepareStatement(builder.toString());
        for(int i=0; i<values.size(); i++)
        {
            setParameter(i+1,values.get(i));
        }
    }

    public Long insert() throws Exception
    {
        ResultSet set = statement.executeQuery();
        if(set.next())
        {
            return set.getLong(1);
        }
        return null;
    }

    @Override
    public InsertQuery setParameter(int index,Object value) throws Exception
    {
        statement.setObject(index,value);
        return this;
    }
}
