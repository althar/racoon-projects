package racoonsoft.select.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.xml.XMLProcessor;
import racoonsoft.library.yandex.direct.YandexDirectPackage;
import racoonsoft.library.yandex.direct.YandexDirectProcessor;
import racoonsoft.library.yandex.direct.method.CreateOrUpdateBanners;
import racoonsoft.library.yandex.direct.method.GetCampaignsList;
import racoonsoft.select.database.PGSQLDataSource;
import racoonsoft.select.structure.CatalogueGood;

import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;

@Controller
@RequestMapping("/yandex")
public class YandexController
{
    private String xmlHeader = "<?xml version=\"1.0\" encoding=\"windows-1251\"?>";
    private String shopName = "Select ST";
    private String companyName = "Select";
    private String shopUrl = "http://select-st.ru";
    private String shopPlatform = "RacoonSoft";
    private String shopVersion = "1.0";
    private String shopAgency = "no";
    private String shopEmail = "selectsanteh@gmail.com";
    private String shopLocalDeliveryCost = "300";
    private String shopBaseGoodUrl = "http://select-st.ru/good?id=";
    private String goodBid = "10";
    private String goodCbid = "15";


    @Autowired
    private PGSQLDataSource dbProc;

    @RequestMapping("/direct")
    public void direct(HttpServletResponse resp) throws Exception
    {
        YandexDirectProcessor proc = new YandexDirectProcessor("60d0abc311f54be9b0ca31c7342a2fea","racoon.dmitry","12b19f9adeb84780919f42b49a86599f",false);
        ArrayList<DBRecord> list = dbProc.getRecords("SELECT * FROM good WHERE available>0 AND sell_price>3000");
        YandexDirectPackage getCompaigns = new GetCampaignsList();
        proc.executeMethod(getCompaigns);

        Long campaignID = 7569574l;
        for(int i=0; i<list.size(); i++)
        {
            String name = list.get(i).getStringValue("name");
            for(int j=0; j<10; j++)
            {
                if(name.length()>33)
                {
                    int index_of = name.lastIndexOf(' ');
                    name = name.substring(0,index_of);
                }
            }
            if(i>=999)
            {
                campaignID = 7569591l;
            }
            name = name.replace(",","").replace("\"","");
            String keyPhrase = name.replace("."," ").replace("\\"," ").replace("/"," ").replace("?"," ").replace("%"," ").replace("#"," ").replace("$"," ").replace("&"," ").replace("("," ").replace(")"," ");
            String link = "http://select-st.ru/good?id="+list.get(i).getLongValue("id");
            link+="&utm_source=yandex&utm_medium=cpc&utm_content=yandex+direct&utm_campaign=pcp+by+item";
            String category = list.get(i).getStringValue("category");
            String price = String.valueOf(list.get(i).getDoubleValue("sell_price").intValue());
            String text = name+".";
            String text_postfix_0 = " "+price+" руб!";
            String text_postfix_1 = " Доставка.";
            String text_postfix_2 = " Гарантия ";
            if(category.toLowerCase().contains("мойки")||category.toLowerCase().contains("смесители")||category.toLowerCase().contains("измельчители")||category.toLowerCase().contains("душевые комплекты"))
            {
                text_postfix_2+="5 лет.";
            }
            else
            {
                text_postfix_2+="1 год.";
            }
            if(category.toLowerCase().contains("мойки")&&!name.toLowerCase().contains("мойка"))
            {
                text = "Мойка "+text;
            }
            if(category.toLowerCase().contains("измельчители")&&!name.toLowerCase().contains("измельчитель"))
            {
                text = "Измельчитель "+text;
            }
            if(category.toLowerCase().contains("смесители")&&!name.toLowerCase().contains("смеситель"))
            {
                text = "Смеситель "+text;
            }
            if(category.toLowerCase().contains("Душевые комплекты")&&!name.toLowerCase().contains("душевой комплект"))
            {
                text = "Душевой комплект "+text;
            }
            if(text.length()+text_postfix_0.length()<=75)
            {
                text+=text_postfix_0;
            }
            if(text.length()+text_postfix_1.length()<=75)
            {
                text+=text_postfix_1;
            }
            if(text.length()+text_postfix_2.length()<=75)
            {
                text+=text_postfix_2;
            }
            CreateOrUpdateBanners create = new CreateOrUpdateBanners();
            create.setMain(0l,campaignID,name,text,link, "1");
            create.setContactInfo("Олег Красников", "Россия","+7","Москва","","","", "","499","3907351","","SELECT","","","","selectsanteh@gmail.com",0,6,8,0,21,0, "1117746810807","12","32","22","23","23","43");
            create.addPhrase(100001l,keyPhrase, "No",5.0,5.0,"Yes","Medium", "","");
//            create.addSiteLink("Select-cool","http://select-st.ru/best");
//            create.addSiteLink("Select-coolD","http://select-st.ru/lmao");
//            create.addSiteLink("Select-coolF","http://select-st.ru/lll");
//            create.addMinusKeyword("Манана");

            proc.executeMethod(create);
        }

    }
    @RequestMapping("/yml")
    public void getYML(HttpServletResponse resp,String bid) throws Exception
    {
        if(bid!=null)
        {
            goodBid=bid;
            goodCbid=bid;
        }
        resp.setCharacterEncoding("windows-1251");
        resp.getWriter().print(yml());
        resp.getWriter().close();
    }
    public String yml() throws Exception
    {
        XMLProcessor yml = new XMLProcessor(false,xmlHeader+"<yml_catalog></yml_catalog>");
        // Date (step 1)
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-d hh:mm");
        String date = formatter.format(new Date());
        yml.setAttribute("yml_catalog","date",date);
        yml.addNode("yml_catalog","shop");

        // Shop (step 2)
        yml.addNode("yml_catalog.shop","name",shopName);
        yml.addNode("yml_catalog.shop","company",companyName);
        yml.addNode("yml_catalog.shop","url",shopUrl);
        yml.addNode("yml_catalog.shop","platform",shopPlatform);
        yml.addNode("yml_catalog.shop","version",shopVersion);
        yml.addNode("yml_catalog.shop","agency",shopAgency);
        yml.addNode("yml_catalog.shop","email",shopEmail);
        yml.addNode("yml_catalog.shop","currencies");
        Node n = yml.addNode("yml_catalog.shop.currencies","currency");
        ((Element)n).setAttribute("id","RUR");
        ((Element)n).setAttribute("rate","1");

        // Categories (step 3)
        yml.addNode("yml_catalog.shop","categories");
        ArrayList<DBRecord> categories = dbProc.getAllCategories();
        for(DBRecord cat: categories)
        {
            Node catNode = yml.addNode("yml_catalog.shop.categories","category",cat.getStringValue("name"));
            ((Element)catNode).setAttribute("id",cat.getLongValue("id").toString());
            if(cat.getLongValue("parent_id")!=null)
            {
                ((Element)catNode).setAttribute("parentId",cat.getLongValue("parent_id").toString());
            }
        }
        yml.addNode("yml_catalog.shop","local_delivery_cost",shopLocalDeliveryCost);

        // Offers
        yml.addNode("yml_catalog.shop","offers");
        ArrayList<CatalogueGood> goods = dbProc.getGoods("","","",1,50000,null,null,null);
        for(CatalogueGood good: goods)
        {
            if(good.getDoubleValue("sell_price")>2000)
            {
                Node offer = yml.addNode("yml_catalog.shop.offers","offer");
                ((Element)offer).setAttribute("id",good.getLongValue("id").toString());
                ((Element)offer).setAttribute("available","true");
                ((Element)offer).setAttribute("type","vendor.model");
                ((Element)offer).setAttribute("bid",goodBid);
                ((Element)offer).setAttribute("cbid",goodCbid);
    //            yml.addNode(offer,"name",good.getStringValue("name"));
                yml.addNode(offer,"url",shopBaseGoodUrl+good.getLongValue("id").toString());
                yml.addNode(offer,"price",Math.round(good.getDoubleValue("sell_price")));
                yml.addNode(offer,"currencyId","RUR");
                yml.addNode(offer,"categoryId",good.getLongValue("category_id"));
                String img = good.getMainImageUrl();
                if(!(img.toLowerCase().contains("http://")||img.toLowerCase().contains("https://")))
                {
                    img = "http://select-st.ru"+img;
                }
                yml.addNode(offer,"picture",img);
                yml.addNode(offer,"store","false");
                yml.addNode(offer,"pickup","false");
                yml.addNode(offer,"delivery","true");
                yml.addNode(offer,"local_delivery_cost",shopLocalDeliveryCost);
                yml.addNode(offer,"vendor",good.getStringValue("brand"));
                yml.addNode(offer,"model",good.getStringValue("name"));
                yml.addNode(offer,"description",good.getStringValue("description"));
                yml.addNode(offer,"sales_notes","Подарок при заказе от 2000 рублей!");
                yml.addNode(offer,"manufacturer_warranty","true");
                HashMap<String,String> characteristics = good.getCharacteristics();
                Iterator<String> characteristicsIter = characteristics.keySet().iterator();
                while(characteristicsIter.hasNext())
                {
                    String key = characteristicsIter.next();
                    String value = characteristics.get(key);
                    Node ch = yml.addNode(offer,"param",value);
                    ((Element)ch).setAttribute("name",key);
                }
                ArrayList<String> images = good.getImages();
                for(String image:images)
                {
    //                String img = image;
    //                if(!(img.toLowerCase().contains("http://")||img.toLowerCase().contains("https://")))
    //                {
    //                    img = "http://select-st.ru"+img;
    //                }
                    yml.addNode(offer,"picture",image);
                }
            }
        }
        String xml = yml.toXMLString("windows-1251");
        xml = xml.replace(xmlHeader,xmlHeader+"<!DOCTYPE yml_catalog SYSTEM \"shops.dtd\">");
        return xml;
    }
}
