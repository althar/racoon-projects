package racoonsoft.library.yandex.direct.method;

import racoonsoft.library.helper.TypeHelper;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.yandex.direct.YandexDirectPackage;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class CreateOrUpdateBanners extends YandexDirectPackage
{
    // Main
    private Long bannerID;
    private Long campaignID;
    private String title;
    private String text;
    private String href;
    private String geo;
    public void setMain(Long bannerID,Long campaignID,String title,String text,String href,String geo)
    {
        this.bannerID = bannerID;
        this.campaignID = campaignID;
        this.title = title;
        this.text = text;
        this.href = href;
        this.geo = geo;
    }

    // ContactInfo
    private HashMap<String,Object> contactInfo = new HashMap<String, Object>();
    public void setContactInfo(String contactPerson
            ,String country
            ,String countryCode
            ,String city
            ,String street
            ,String house
            ,String build
            ,String apart
            ,String cityCode
            ,String phone
            ,String phoneExt
            ,String companyName
            ,String imclient
            ,String imLogin
            ,String extraMessage
            ,String contactEmail
            ,int dayFrom
            ,int dayTo
            ,int hourFrom
            ,int minuteFrom
            ,int hourTo
            ,int minuteTo
            ,String ogrn
            ,String x
            ,String y
            ,String x1
            ,String y1
            ,String x2
            ,String y2)
    {
        String workTime = String.valueOf(dayFrom)+";"
                +String.valueOf(dayTo)+";"
                +String.valueOf(hourFrom)+";"
                +String.valueOf(minuteFrom)+";"
                +String.valueOf(hourTo)+";"
                +String.valueOf(minuteTo)+";";
        HashMap<String,String> pointOnMap = new HashMap<String, String>();
        pointOnMap.put("x",x);
        pointOnMap.put("y",y);
        pointOnMap.put("x1",x1);
        pointOnMap.put("y1",y1);
        pointOnMap.put("x2",x2);
        pointOnMap.put("y2",y2);
        contactInfo = new HashMap<String, Object>();
        contactInfo.put("ContactPerson",contactPerson);
        contactInfo.put("Country",country);
        contactInfo.put("CountryCode",countryCode);
        contactInfo.put("City",city);
        contactInfo.put("Street",street);
        contactInfo.put("House",house);
        contactInfo.put("Build",build);
        contactInfo.put("Apart",apart);
        contactInfo.put("CityCode",cityCode);
        contactInfo.put("Phone",phone);
        contactInfo.put("PhoneExt",phoneExt);
        contactInfo.put("CompanyName",companyName);
        contactInfo.put("IMClient",imclient);
        contactInfo.put("IMLogin",imLogin);
        contactInfo.put("ExtraMessage",extraMessage);
        contactInfo.put("ContactEmail",contactEmail);
        contactInfo.put("OGRN",ogrn);
        contactInfo.put("WorkTime",workTime);
        contactInfo.put("PointOnMap",pointOnMap);

    }

    // Phrases
    private ArrayList<HashMap<String,Object>> phrases = new ArrayList<HashMap<String, Object>>();
    public void addPhrase(Long phraseId,String phrase,String isRubric,Double price, Double contextPrice, String autoBroker, String autoBudgetPriority, String userParam1, String userParam2)
    {
        for(HashMap<String,Object> map:phrases)
        {
            if((Long)map.get("phraseID")==phraseId.longValue())
            {
                phrases.remove(map);
                break;
            }
        }
        HashMap<String,Object> map = new HashMap<String, Object>();
        map.put("PhraseID",phraseId);
        map.put("Phrase",phrase);
        map.put("IsRubric",isRubric);
        map.put("Price",price);
        map.put("ContextPrice",contextPrice);
        map.put("AutoBroker",autoBroker);
        map.put("AutoBudgetPriority",autoBudgetPriority);
        HashMap<String,String> params = new HashMap<String, String>();
        params.put("Param1",userParam1);
        params.put("Param2",userParam2);
        map.put("UserParams",params);
        phrases.add(map);
    }
    public void removePhrase(Long phraseId)
    {
        for(HashMap<String,Object> map:phrases)
        {
            if((Long)map.get("phraseId")==phraseId.longValue())
            {
                phrases.remove(map);
                break;
            }
        }
    }

    // Site links
    ArrayList<HashMap<String,String>> sitelinks = new ArrayList<HashMap<String, String>>();
    public void addSiteLink(String title,String href)
    {
        HashMap<String,String> map = new HashMap<String, String>();
        map.put("Title",title);
        map.put("Href",href);
        sitelinks.add(map);
    }

    // Minus keywords
    private ArrayList<String> minusKeywords = new ArrayList<String>();
    public void addMinusKeyword(String keyword)
    {
        minusKeywords.add(keyword);
    }
    public void removeMinusKeyword(String minusKeyword)
    {
        minusKeywords.remove(minusKeyword);
    }

    @Override
    public JSONProcessor getJsonPackage(String application_id, String login, String access_token) throws Exception {
        pack = new JSONProcessor(new HashMap<String, Object>());
        HashMap<String,Object> struct = pack.getStructure();
        HashMap<String,Object> innerParam = new HashMap<String, Object>();
        struct.put("method","CreateOrUpdateBanners");
        struct.put("login",login);
        struct.put("token",access_token);
        struct.put("application_id", application_id);

        ArrayList<Object> param = new ArrayList<Object>();

        // Main
        innerParam.put("CampaignID", campaignID);
        innerParam.put("Title", title);
        innerParam.put("Href", href);
        innerParam.put("Text",text);
        innerParam.put("Geo",geo);
        if(bannerID!=null)
        {
            innerParam.put("BannerID",bannerID);
        }

        // Contact info
        innerParam.put("ContactInfo",contactInfo);

        // Phrases
        innerParam.put("Phrases",phrases);

        // Site links
        innerParam.put("Sitelinks",sitelinks);

        // Minus keywords
        innerParam.put("MinusKeywords",minusKeywords);

        param.add(innerParam);

        struct.put("param",param);

        pack = new JSONProcessor(struct);
        System.out.println("==="+pack.toJsonString());
        return pack;
    }
}
