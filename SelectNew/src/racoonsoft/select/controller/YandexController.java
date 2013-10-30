package racoonsoft.select.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.xml.XMLProcessor;
import racoonsoft.select.database.PGSQLDataSource;
import racoonsoft.select.structure.CatalogueGood;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
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
    private String shopBaseGoodUrl = "http://select-st.ru/%D0%A2%D0%BE%D0%B2%D0%B0%D1%80?id=";
    private String goodBid = "10";
    private String goodCbid = "15";


    @Autowired
    private PGSQLDataSource dbProc;


    @RequestMapping("/yml")
    public void getYML(HttpServletResponse resp) throws Exception
    {
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
        ArrayList<CatalogueGood> goods = dbProc.getGoods("","","",1,5000,null,null,null);
        for(CatalogueGood good: goods)
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
            yml.addNode(offer,"sales_notes","10% скидка при первом заказе.");
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
        String xml = yml.toXMLString("windows-1251");
        xml = xml.replace(xmlHeader,xmlHeader+"<!DOCTYPE yml_catalog SYSTEM \"shops.dtd\">");
        return xml;
    }
}
