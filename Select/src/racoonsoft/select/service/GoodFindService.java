package racoonsoft.select.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import racoonsoft.library.access.ActionResult;
import racoonsoft.library.database.DBRecord;
import racoonsoft.library.helper.StringHelper;
import racoonsoft.library.http.HTTPClient;
import racoonsoft.select.database.PGSQLDataSource;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

@Service
public class GoodFindService
{
    @Autowired
    private PGSQLDataSource dbProc;

    public static String imageBaseUrl = "C://materials";
    public ActionResult extractGoodsMoykiRu() throws Exception
    {
        int success = 0;
        int failure = 0;
        ActionResult result = new ActionResult(ActionResult.ACTION_SUCCESSFUL);
        int[][] waterprint = new int[104][37];
        int[][] waterprint_small = new int[89][14];
        BufferedImage water_img = ImageIO.read(new File("C://materials/select-waterprint.png"));
        for(int le=0; le<104; le++)
        {
            for(int to=0; to<37; to++)
            {
                waterprint[le][to] = water_img.getRGB(le,to);
            }
        }
        BufferedImage water_img_small = ImageIO.read(new File("C://materials/select-waterprint-small.png"));
        for(int le=0; le<89; le++)
        {
            for(int to=0; to<14; to++)
            {
                waterprint_small[le][to] = water_img_small.getRGB(le,to);
            }
        }
        // Get catalogue pages
        ArrayList<String> goodPages = new ArrayList<String>();
        for(int i=1; i<60; i++)
        {
            String catPage = HTTPClient.sendHTTPRequestWithMethod("http://moyki.ru/product/select?cat=1&Product_page="+i,"GET");
            String regexp = "\\/product\\/[0-9]{1,5}";
            ArrayList<String> goodLinks = StringHelper.findSubstring(catPage,regexp,true);
            goodPages.addAll(goodLinks);
        }
        for(int i=1; i<60; i++)
        {
            String catPage = HTTPClient.sendHTTPRequestWithMethod("http://moyki.ru/product/select?cat=2&Product_page="+i,"GET");
            String regexp = "\\/product\\/[0-9]{1,5}";
            ArrayList<String> goodLinks = StringHelper.findSubstring(catPage,regexp,true);
            goodPages.addAll(goodLinks);
        }
        for(int i=1; i<60; i++)
        {
            String catPage = HTTPClient.sendHTTPRequestWithMethod("http://moyki.ru/product/select?cat=16&Product_page="+i,"GET");
            String regexp = "\\/product\\/[0-9]{1,5}";
            ArrayList<String> goodLinks = StringHelper.findSubstring(catPage,regexp,true);
            goodPages.addAll(goodLinks);
        }
        for(int i=1; i<60; i++)
        {
            String catPage = HTTPClient.sendHTTPRequestWithMethod("http://moyki.ru/product/select?cat=17&Product_page="+i,"GET");
            String regexp = "\\/product\\/[0-9]{1,5}";
            ArrayList<String> goodLinks = StringHelper.findSubstring(catPage,regexp,true);
            goodPages.addAll(goodLinks);
        }
        for(int i=1; i<60; i++)
        {
            String catPage = HTTPClient.sendHTTPRequestWithMethod("http://moyki.ru/product/select?cat=19&Product_page="+i,"GET");
            String regexp = "\\/product\\/[0-9]{1,5}";
            ArrayList<String> goodLinks = StringHelper.findSubstring(catPage,regexp,true);
            goodPages.addAll(goodLinks);
        }
        // Extract goods` data
        for(int i=0; i<goodPages.size(); i++)
        {
            String brand = "";
            String article = "";
            String description = "";
            ArrayList<String> characteristics = new ArrayList<String>();
            String goodPage = HTTPClient.sendHTTPRequestWithMethod("http://moyki.ru"+goodPages.get(i),"GET");

            String brandRegexp = "\\<span style\\=\"font\\-size\\:11px\\;\">.*\\<";
            ArrayList<String> brandArr = StringHelper.findSubstring(goodPage,brandRegexp,true);
            if(brandArr.size()>0)
            {
                brand = brandArr.get(0).replace("<span style=\"font-size:11px;\">","").replace("<","");
            }

            String articleRegexp = "<b>Артикул: .*</b>";
            ArrayList<String> articleArr = StringHelper.findSubstring(goodPage,articleRegexp,true);
            if(articleArr.size()>0)
            {
                article = articleArr.get(0).replace("<b>Артикул: ","").replace("</b>","");
            }

            String descriptionRegexp = "\\<div style=\"color\\:\\#333\\;font\\-size\\:12px\\; font\\-style\\: italic\\; padding\\:3px\\;\"\\>.*\\<\\/div\\>";
            ArrayList<String> descriptionArr = StringHelper.findSubstring(goodPage,descriptionRegexp,true);
            if(descriptionArr.size()>0)
            {
                description = descriptionArr.get(0).replace("<div style=\"color:#333;font-size:12px; font-style: italic; padding:3px;\">","").replace("</div>","");
            }

            System.out.println(description);

            // Save images
            try
            {
                //<editor-fold desc="Scheme">
                String scheme_url = "http://moyki.ru"+goodPages.get(i).replace("product","product/scheme");

                URL url = null;
                URLConnection con = null;
                url = new URL(scheme_url);
                con = url.openConnection();
                BufferedInputStream bis = new BufferedInputStream(con.getInputStream());
                File f = new File("C://materials");
                f.mkdirs();
                int j=0;
                String scheme_name = goodPages.get(i).replace("/product/","scheme")+".png";
                String scheme_path = "C://materials/"+goodPages.get(i).replace("/product/","scheme")+".png";
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(scheme_path));
                while ((j = bis.read()) != -1) {
                    //Thread.sleep(1);
                    bos.write(j);
                }
                bos.flush();
                bos.close();
                bis.close();
                //</editor-fold>
                //<editor-fold desc="Image">
                String image_url = "http://moyki.ru"+goodPages.get(i).replace("product","product/image");
                url = new URL(image_url);
                con = url.openConnection();
                bis = new BufferedInputStream(con.getInputStream());

                f = new File(imageBaseUrl);
                f.mkdirs();
                j=0;
                String image_path = "C://materials/"+goodPages.get(i).replace("/product/","image")+".png";
                String image_name = goodPages.get(i).replace("/product/","image")+".png";
                bos = new BufferedOutputStream(new FileOutputStream(image_path));
                while ((j = bis.read()) != -1) {
                    //Thread.sleep(1);
                    bos.write(j);
                }
                bos.flush();
                bos.close();
                bis.close();
                //</editor-fold>

                //<editor-fold desc="Cleanup images">
                BufferedImage img = ImageIO.read(new File(image_path));
                int left = img.getWidth()-104;
                int top = img.getHeight()-38;
                //97x33
                for(int le=0; le<99; le++)
                {
                    for(int to=0; to<33; to++)
                    {
                        img.setRGB(left+le,top+to,Integer.MAX_VALUE);
                    }
                }
                BufferedImage small_img = createResizedCopy(img,128,128,true);
                left = img.getWidth()-104;
                top = img.getHeight()-38;
                //97x33
                for(int le=0; le<99; le++)
                {
                    for(int to=0; to<33; to++)
                    {
                        img.setRGB(left+le,top+to,waterprint[le][to]);
                    }
                }
                ImageIO.write(img,"png",new File(image_path));
                img = ImageIO.read(new File(scheme_path));
                left = img.getWidth()-104;
                top = img.getHeight()-38;
                //97x33
                for(int le=0; le<99; le++)
                {
                    for(int to=0; to<33; to++)
                    {
                        img.setRGB(left+le,top+to,waterprint[le][to]);
                    }
                }
                ImageIO.write(img,"png",new File(scheme_path));
                //</editor-fold>
                String scheme_w = String.valueOf(img.getWidth());
                String scheme_h = String.valueOf(img.getHeight());

                left = small_img.getWidth()-89;
                top = small_img.getHeight()-16;
                for(int le=0; le<89; le++)
                {
                    for(int to=0; to<14; to++)
                    {
                        small_img.setRGB(left+le,top+to,waterprint_small[le][to]);
                    }
                }
                ImageIO.write(small_img,"png",new File(image_path.replace(".png","_small.png")));

                DBRecord good = dbProc.getRecord("SELECT * FROM good WHERE article='" + article + "' AND lower(brand)=lower('" + brand + "')");
                if(good!=null)
                {
                    dbProc.executeNonQuery("DELETE FROM good_image WHERE good_id="+good.getID());
                    dbProc.executeNonQuery("UPDATE good SET description='"+description.replace("'","`")+"' WHERE id="+good.getID());
                    dbProc.executeNonQuery("INSERT INTO good_image (good_id,is_main,url,size,is_our_side,type) VALUES ("+good.getID()+",true,'/img/good/"+image_name+"','300x300',true,'main')");
                    dbProc.executeNonQuery("INSERT INTO good_image (good_id,is_main,url,size,is_our_side,type) VALUES ("+good.getID()+",false,'/img/good/"+image_name.replace(".png","_small.png")+"','128x128',true,'thumb')");
                    dbProc.executeNonQuery("INSERT INTO good_image (good_id,is_main,url,size,is_our_side,type) VALUES ("+good.getID()+",false,'/img/good/"+scheme_name+"','"+scheme_w+"x"+scheme_h+"',true,'scheme')");
                    success++;
                }
                else
                {
                    failure++;
                }
            }
            catch(Exception ex)
            {
                System.out.println(ex.toString());
            }
        }
        result.setData("successCount",success);
        result.setData("failureCount",failure);
        return result;
    }

    public static BufferedImage createResizedCopy(Image originalImage,
                                    int scaledWidth, int scaledHeight,
                                    boolean preserveAlpha)
    {
        System.out.println("resizing...");
        int imageType = preserveAlpha ? BufferedImage.TYPE_INT_RGB : BufferedImage.TYPE_INT_ARGB;
        BufferedImage scaledBI = new BufferedImage(scaledWidth, scaledHeight, imageType);
        Graphics2D g = scaledBI.createGraphics();
        if (preserveAlpha) {
            g.setComposite(AlphaComposite.Src);
        }
        g.drawImage(originalImage, 0, 0, scaledWidth, scaledHeight, null);
        g.dispose();
        return scaledBI;
    }
}
