package racoonsoft.select.structure;

import org.w3c.dom.Node;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.xml.XMLProcessor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.SQLException;
import java.util.*;

public class Basket
{

    private HashMap<Long,Good> goods = new HashMap<Long, Good>();
    private HashMap<String,Double> certificates = new HashMap<String, Double>();
    private Double firstOrderDiscount = 0.0;
    private Long deliveryVariant = 1l;
    private Date deliveryDate = null;
    private String address;
    private String comment;

    public HashMap<Long, Good> getGoods() {
        return goods;
    }
    public Long getTotalCount()
    {
        Long count = 0l;
        Iterator<Good> goodIter = goods.values().iterator();
        while(goodIter.hasNext())
        {
            Good g = goodIter.next();
            count+=g.getCount();
        }
        return count;
    }
    public void setGoods(HashMap<Long, Good> goods) {
        this.goods = goods;
    }

    public XMLProcessor getXml() throws Exception
    {
        XMLProcessor xml = new XMLProcessor(true,"<?xml version=\"1.0\" encoding=\"utf-8\"?><basket></basket>");
        xml.addNode("basket","goods");
        Iterator<Long> goodIter = goods.keySet().iterator();
        while(goodIter.hasNext())
        {
            Long goodId = goodIter.next();
            Good good = goods.get(goodId);
            Node goodNode = xml.addNode("basket.goods","good",good);
            xml.addNode(goodNode,"count",good.getCount());
            xml.addNode(goodNode,"total_sell_price",good.getCount()*good.getDoubleValue("sell_price"));
        }
        xml.addNode("basket","certificates");
        Iterator<String> certIter = certificates.keySet().iterator();
        while(certIter.hasNext())
        {
            String certName = certIter.next();
            Double value = certificates.get(certName);
            Node certNode = xml.addNode("basket.certificates","certificate");
            xml.addNode(certNode,"name",certName);
            xml.addNode(certNode,"value",value);
        }
        xml.addNode("basket","firstOrderDiscount",firstOrderDiscount);
        xml.addNode("basket","deliveryPrice",getDeliveryPrice());
        xml.addNode("basket","deliveryDate",deliveryDate);
        xml.addNode("basket","address",address);
        xml.addNode("basket","comment",comment);
        xml.addNode("basket","totalCount",getTotalCount());
        xml.addNode("basket","totalDiscount",getTotalDiscount());
        xml.addNode("basket","totalGoodPrice",getTotalGoodPrice());
        xml.addNode("basket","totalPrice",getTotalPrice());

        return xml;
    }

    public void clear()
    {
        goods = new HashMap<Long, Good>();
        certificates = new HashMap<String, Double>();
        firstOrderDiscount = 0.0;
        Long deliveryVariant = 1l;
        Date deliveryDate = null;
        String address = null;
        String comment = null;
    }
    public Date getDeliveryDate() {
        return deliveryDate;
    }
    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
    public void addOne(Long goodId) throws SQLException{
        Good g = goods.get(goodId);
        if(g==null)
        {
            g = new Good(BasketStorage.dbProc.getRecord("SELECT g.*,im.url AS good_image FROM good g, good_image im WHERE im.good_id=g.id AND im.is_main=TRUE AND g.id="+goodId+" LIMIT 1"));
            goods.put(goodId,g);
        }
        else
        {
            g.addOne();
        }
    }
    public void setGood(Long goodId,Integer count) throws Exception
    {
        Good g = goods.get(goodId);
        if(g==null)
        {
            g = new Good(BasketStorage.dbProc.getRecord("SELECT g.*,im.url AS good_image FROM good g, good_image im WHERE im.good_id=g.id AND im.is_main=TRUE AND g.id="+goodId+" LIMIT 1"));
            goods.put(goodId, g);
        }
        if(count==0)
        {
            goods.remove(goodId);
            return;
        }
        g.setCount(count);
    }
    public void removeOne(Long goodId) throws SQLException
    {
        Good g = goods.get(goodId);
        if(g==null)
        {
            return;
        }
        if(!g.removeOne())
        {
            goods.remove(goodId);
        }
    }
    public void setCertificate(String key,Double value)
    {
        certificates.put(key,value);
    }
    public void removeCertificate(String key)
    {
        certificates.remove(key);
    }
    public HashMap<String, Double> getCertificates()
    {
        return certificates;
    }
    public Double getCertificateValue(String key)
    {
        return round(certificates.get(key));
    }
    public Double getDeliveryPrice() throws Exception
    {
        return 0.0;
    }
    public Long getDeliveryVariant()
    {
        return deliveryVariant;
    }
    public void setDeliveryVariant(Long deliveryVariantId) throws SQLException
    {
        deliveryVariant = deliveryVariantId;
    }
    public Double getFirstOrderDiscount()
    {
        return round(firstOrderDiscount);
    }
    public void setFirstOrderDiscount(Double firstOrderDiscount)
    {
        this.firstOrderDiscount = firstOrderDiscount;
    }
    public Double getTotalGoodPrice()
    {
        Double res = 0.0;
        Collection<Good> goodCollection = goods.values();
        for(Good g:goodCollection)
        {
            Double price = g.getDoubleValue("sell_price")*g.getCount();
            res+=price;
        }
        return round(res);
    }
    public Double getTotalCertificatesDiscount()
    {
        Double res = 0.0;
        Collection<Double> goodCollection = certificates.values();
        for(Double value:goodCollection)
        {
            res+=value;
        }
        return round(res);
    }
    public Double getTotalDiscount()
    {
        Double res = 0.0;
        res+=getFirstOrderDiscount()+ getTotalCertificatesDiscount();
        return round(res);
    }
    public Double getTotalPrice() throws Exception
    {
        return round(getTotalGoodPrice()+getDeliveryPrice()-getTotalDiscount());
    }
    public String getAddress()
    {
        return address;
    }
    public void setAddress(String address)
    {
        this.address = address;
    }
    public String getComment()
    {
        return comment;
    }
    public void setComment(String comment)
    {
        this.comment = comment;
    }
    public boolean isEmpty()
    {
        return goods.size()==0;
    }
    public static Double round(Double val)
    {
        return round(val,2);
    }
    public static Double round(Double val,Integer digits)
    {
        return new BigDecimal(val).setScale(digits, RoundingMode.HALF_UP).doubleValue();
    }
}
