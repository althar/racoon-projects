package racoonsoft.investmentwheel.controller.api;

import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;

@Controller
public class InvestmentWheelController
{
    public HashMap<String,Object> getParameters(HttpServletRequest request)
    {
        HashMap<String,Object> result = new HashMap<String, Object>();
        Enumeration<String> paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements())
        {
            String key = paramNames.nextElement();
            String value = request.getParameter(key);
            result.put(key,value);
        }
        return result;
    }
}
