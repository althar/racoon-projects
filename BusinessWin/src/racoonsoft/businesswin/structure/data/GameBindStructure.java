package racoonsoft.businesswin.structure.data;

import racoonsoft.library.annotations.DataStructureField;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Enumeration;

public class GameBindStructure
{
    private String lastParsedField = "";

    public String getLastParsedField()
    {
        return lastParsedField;
    }

    public void fill(HttpServletRequest request) throws NumberFormatException, Exception
    {
        Enumeration<String> param_names = request.getParameterNames();
        while(param_names.hasMoreElements())
        {
            String name = param_names.nextElement();
            String value = request.getParameter(name);
            Field[] fields = this.getClass().getDeclaredFields();
            for(int i=0; i<fields.length; i++)
            {
                Field f = fields[i];
                Class<?> type = f.getType();
                if(f.isAnnotationPresent(DataStructureField.class))
                {
                    DataStructureField field_annotation = f.getAnnotation(DataStructureField.class);
                    if(field_annotation.name().equalsIgnoreCase(name))
                    {
                        lastParsedField = f.getName();
                        if(type.isAssignableFrom(Class.forName("java.lang.String")))
                        {
                            f.setAccessible(true);
                            f.set(this,value);
                        }
                        if(type.isAssignableFrom(Class.forName("java.lang.Double")))
                        {
                            f.setAccessible(true);
                            f.set(this,Double.valueOf(value));
                        }
                        if(type.isAssignableFrom(Class.forName("java.lang.Integer")))
                        {
                            f.setAccessible(true);
                            f.set(this,Integer.valueOf(value));
                        }
                        if(type.isAssignableFrom(Class.forName("java.lang.Long")))
                        {
                            f.setAccessible(true);
                            f.set(this,Long.valueOf(value));
                        }
                        if(type.isAssignableFrom(EconomicsValue.class))
                        {
                            f.setAccessible(true);
                            EconomicsValue valueField = (EconomicsValue)f.get(this);
                            valueField.set(Double.valueOf(value));
                            f.set(this, valueField);
                        }
                    }
                }
            }
        }

    }
}
