package racoonsoft.library.payture;

import racoonsoft.library.xml.XMLProcessor;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

import racoonsoft.library.http.*;

import java.util.HashMap;

public abstract class PaytureRequest
{
    protected HashMap<String,Object> requestFields;
    protected HashMap<String,String> responseFields;

    protected String apiHost;
    protected String merchantApi;
    protected String requestName;

    public String getResponseParameter(String name)
    {
        return responseFields.get(name);
    }
    public boolean getSuccess()
    {
        return Boolean.valueOf(getResponseParameter("Success"));
    }
    protected abstract String generateParameterString() throws UnsupportedEncodingException;
    public boolean doRequest() throws MalformedURLException, IOException, UnsupportedEncodingException, URISyntaxException, ParserConfigurationException, SAXException {
        String response = HTTPClient.sendHTTPSRequestWithMethod(apiHost+"/"+requestName+generateParameterString(),"GET");
        System.out.println(response);
        extractResponse(response);

        return getSuccess();
    }
    private void extractResponse(String responseBody) throws ParserConfigurationException, SAXException, IOException
    {
        XMLProcessor proc = new XMLProcessor(responseBody);
        NamedNodeMap attr = proc.getAttributes(requestName);
        for(int i=0; i<attr.getLength(); i++)
        {
            Node item = attr.item(i);
            String name = item.getNodeName();
            String value = item.getNodeValue();

            responseFields.put(name,value);
        }
    }
}
