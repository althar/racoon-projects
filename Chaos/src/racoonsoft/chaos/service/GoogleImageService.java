package racoonsoft.chaos.service;

import org.springframework.stereotype.Service;
import racoonsoft.chaos.entity.Image;
import racoonsoft.chaos.entity.ImageList;
import racoonsoft.library.http.HTTPClient;
import racoonsoft.library.json.JSONProcessor;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

@Service
public class GoogleImageService
{
    private final String imageSearchURL = "https://ajax.googleapis.com/ajax/services/search/images?rsz=8&v=1.0&q=";

    private String getHTMLWithImages(String keyword) throws IOException
    {

        HashMap<String,String> pars = new HashMap<String, String>();
        pars.put("Accept","text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");
        pars.put("Host","ajax.googleapis.com");
        pars.put("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.31 (KHTML, like Gecko) Chrome/26.0.1410.64 Safari/537.31");
        pars.put("Accept-Charset","windows-1251,utf-8;q=0.7,*;q=0.3");
        pars.put("Accept-Encoding","gzip,deflate,sdch");
        pars.put("Accept-Language","ru-RU,ru;q=0.8,en-US;q=0.6,en;q=0.4");
        pars.put("Cache-Control","max-age=0");
        pars.put("Connection","keep-alive");
        String response = HTTPClient.sendHTTPSRequest(imageSearchURL + keyword, "GET");
        //RequestWithMethod(imageSearchURL + keyword,"GET",pars);
        return response;
    }
    public ImageList getImages(String[] keywords) throws Exception
    {
        ImageList list = new ImageList();
        for(String keyword:keywords)
        {
            String html = getHTMLWithImages(URLEncoder.encode(keyword,"UTF-8"));
            JSONProcessor json = new JSONProcessor(html);
            ArrayList<HashMap<String,String>> imageInfos = (ArrayList<HashMap<String, String>>) json.getValue("responseData.results");
            for(HashMap<String,String> map:imageInfos)
            {
                Image im = new Image();
                im.keyword = keyword;
                im.contentNoFormatting = map.get("contentNoFormatting");
                im.tbUrl = map.get("tbUrl");
                im.height = Integer.valueOf(map.get("height"));
                im.width = Integer.valueOf(map.get("width"));
                im.titleNoFormatting = map.get("titleNoFormatting");
                im.unescapedUrl = map.get("unescapedUrl");
                list.addImage(im);
            }
        }

        return list;
    }

}
