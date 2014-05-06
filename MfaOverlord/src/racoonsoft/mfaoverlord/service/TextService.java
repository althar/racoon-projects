package racoonsoft.mfaoverlord.service;

import racoonsoft.mfaoverlord.structure.StolenArticle;
import racoonsoft.racoonspring.helper.StringHelper;

import java.util.ArrayList;

public class TextService
{
    private static String googleSearchResultLinksRegexp = "<h3 class=\"r\"><a[\\s\\S]+?</a>";
    private static String hrefRegexp = "href=\"[\\s\\S]+?\"";
    private static String articleDLRegexp = "<dl[\\s\\S]+?<\\/dl>";
    private static String articleDTRegexp = "<dt[\\s\\S]+?<\\/dt>";
    private static String articleDDRegexp = "<dd[\\s\\S]+?<\\/dd>";
    private static String articleDTDDRegexp = "<dt[\\s\\S]+?<\\/dt>[\\s\\S]+?<dd[\\s\\S]+?<\\/dd>";

    private static String hRegexp = "<h1[\\s\\S]+?<\\/h1>|<h2[\\s\\S]+?<\\/h2>|<h3[\\s\\S]+?<\\/h3>";
    private static String pRegexp = "<p[\\s\\S]+?<\\/p>|<span[\\s\\S]+?<\\/span>";

    public static String getDomain(String url)
    {
        int indexOf = url.indexOf("://");
        int last = url.indexOf("/",indexOf+4);
        return url.substring(0,last);
    }
    public static ArrayList<String> getGoogleResult(String text)
    {
        ArrayList<String> h3s = StringHelper.findSubstring(text, googleSearchResultLinksRegexp, true);
        ArrayList<String> hrefs = new ArrayList<String>();
        for(String st: h3s)
        {
            ArrayList<String> href = StringHelper.findSubstring(st, hrefRegexp, true);
            if(href!=null&&href.size()>0)
            {
                hrefs.add(href.get(0).replace("href=\"","").replace("\"",""));
            }
        }
        return hrefs;
    }

    public static ArrayList<StolenArticle> getArticles(String text,String domain,String keyword)
    {
        ArrayList<StolenArticle> result = new ArrayList<StolenArticle>();

        //<editor-fold desc="DL & DT & DD">
        ArrayList<String> dls = StringHelper.findSubstring(text, articleDLRegexp, true);
        for(String dl:dls)
        {
            ArrayList<String> dt_dd = StringHelper.findSubstring(dl, articleDTDDRegexp, true);
            for(String dtdd:dt_dd)
            {
                ArrayList<String> dt = StringHelper.findSubstring(dtdd, articleDTRegexp, true);
                ArrayList<String> dd = StringHelper.findSubstring(dtdd, articleDDRegexp, true);
                if(dt.size()>0&&dd.size()>0)
                {
                    StolenArticle article = new StolenArticle();
                    article.title = dt.get(0).replace("<dt>","").replace("</dt>","");
                    article.text = "<dl>"+dtdd+"</dl>";
                    article.domain = domain;
                    ArrayList<String> letters = StringHelper.findSubstring(article.text, "[а-яА-Я]", false);
                    if(article.text.length()>120&&letters.size()>150)
                    {
                        result.add(article);
                    }
                }
            }
        }
        //</editor-fold>

        //<editor-fold desc="<H> and <p>">
        ArrayList<String> hs = StringHelper.findSubstring(text, hRegexp, true);
        ArrayList<String> ps = StringHelper.findSubstring(text, pRegexp, true);
        int i=0;
        for(i=0; i<hs.size()&&i<ps.size(); i++)
        {
            StolenArticle article = new StolenArticle();
            article.title = removeTags(hs.get(i).replace("<h1>","").replace("<h2>","").replace("<h3>","")
                    .replace("</h1>","").replace("</h2>","").replace("</h3>",""));
            article.text = normalizeLinks(ps.get(i),domain);
            article.domain = domain;
            ArrayList<String> letters = StringHelper.findSubstring(article.text, "[а-яА-Я]", false);
            if(letters.size()>150)
            {
                result.add(article);
            }
        }
        // All else...
        for(i=i; i<ps.size(); i++)
        {
            StolenArticle article = new StolenArticle();
            article.title = keyword;
            article.text = normalizeLinks(ps.get(i),domain);
            article.domain = domain;
            ArrayList<String> letters = StringHelper.findSubstring(article.text, "[а-яА-Я]", false);
            if(letters.size()>150)
            {
                result.add(article);
            }
        }

        //</editor-fold>
        return result;
    }

    public static String removeTags(String text)
    {
        ArrayList<String> tags = StringHelper.findSubstring(text, "<[\\s\\S]+?>", false);
        for(String tag: tags)
        {
            text = text.replace(tag,"");
        }
        return text;

    }
    public static String normalizeLinks(String text,String domain)
    {
        text = text.replace("href=\"/","href=\""+domain+"/");
        text = text.replace("src=\"/","src=\""+domain+"/");
        return text;
    }
}
