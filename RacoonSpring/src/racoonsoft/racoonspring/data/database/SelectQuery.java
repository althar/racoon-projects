package racoonsoft.racoonspring.data.database;

import com.rits.cloning.Cloner;
import racoonsoft.racoonspring.data.annotation.DataStructureFieldExclude;
import racoonsoft.racoonspring.data.annotation.DataStructureTable;
import racoonsoft.racoonspring.data.structure.DatabaseStructure;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class SelectQuery extends Query
{
    public SelectQuery(Connection c,String query) throws Exception
    {
        conn = c;
        statement = conn.prepareStatement(query);
    }
    public SelectQuery(Connection c,Class<?> dataClass,String condition) throws Exception
    {
        Field[] fs = dataClass.getDeclaredFields();
        String tableName = dataClass.getAnnotation(DataStructureTable.class).name();
        StringBuilder builder = new StringBuilder("SELECT ");
        for(int i=0; i<fs.length; i++)
        {
            if(!fs[i].isAnnotationPresent(DataStructureFieldExclude.class))
            {
                builder.append(fs[i].getName());
                if(i-1<fs.length)
                {
                    builder.append(",");
                }
            }
        }
        builder.append(" FROM ");
        builder.append(tableName);
        builder.append(" WHERE ");
        builder.append(condition);
        conn = c;
        statement = conn.prepareStatement(builder.toString());
    }

    public DatabaseStructure selectOne() throws Exception
    {
        ArrayList<DatabaseStructure> res = select();
        if(res!=null&&res.size()>0)
        {
            return res.get(0);
        }
        return null;
    }
    public <T extends DatabaseStructure> T selectOne(T c) throws Exception
    {
        ArrayList<T> res = select(c);
        if(res!=null&&res.size()>0)
        {
            return res.get(0);
        }
        return null;
    }
    public ArrayList<DatabaseStructure> select() throws Exception
    {
        ResultSet set = statement.getResultSet();
        int column_count = set.getMetaData().getColumnCount();
        ArrayList<DatabaseStructure> result = new ArrayList<DatabaseStructure>();
        String[] column_names = new String[column_count];
        for(int i=0; i<column_count; i++)
        {
            column_names[i] = set.getMetaData().getColumnName(i+1);
        }
        while(set.next())
        {
            DatabaseStructure s = new DatabaseStructure();
            for(int i=0; i<column_count; i++)
            {
                s.set(column_names[i], set.getObject(column_names[i]));
            }
            result.add(s);
        }
        return result;
    }
    public <T extends DatabaseStructure> ArrayList<T> select(T c) throws Exception
    {
        ResultSet set = statement.getResultSet();
        int column_count = set.getMetaData().getColumnCount();
        ArrayList<T> result = new ArrayList<T>();
        String[] column_names = new String[column_count];
        for(int i=0; i<column_count; i++)
        {
            column_names[i] = set.getMetaData().getColumnName(i+1);
        }
        while(set.next())
        {
            Cloner cl = new Cloner();
            T cc = cl.deepClone(c);
            for(int i=0; i<column_count; i++)
            {
                cc.set(column_names[i], set.getObject(column_names[i]));
            }
            result.add(cc);
        }
        return result;
    }
}
