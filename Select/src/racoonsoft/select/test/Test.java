package racoonsoft.select.test;

import racoonsoft.library.http.HTTPClient;
import racoonsoft.library.yandex.direct.method.CreateOrUpdateBanners;
import racoonsoft.library.yandex.direct.method.GetCampaignsList;
import racoonsoft.library.yandex.direct.YandexDirectPackage;
import racoonsoft.library.yandex.direct.YandexDirectProcessor;

import java.io.FileInputStream;
import java.util.concurrent.ThreadPoolExecutor;

public class Test
{
    static Integer i=0;
    public static void main(String[] args)
    {
        try
        {

            FileInputStream correct = new FileInputStream("C:/temp/correct.xml");
            FileInputStream incorrect = new FileInputStream("C:/temp/incorrect.xml");
            while(correct.available()>0&&incorrect.available()>0)
            {
                Integer corr = correct.read();
                Integer incorr = incorrect.read();
                String corStr = new String(new byte[]{corr.byteValue()},"windows-1251");
                String incorStr = new String(new byte[]{incorr.byteValue()},"windows-1251");
                System.out.println(corr+"  "+incorr);
            }
//            YandexDirectProcessor proc = new YandexDirectProcessor("60d0abc311f54be9b0ca31c7342a2fea","racoon.dmitry","12b19f9adeb84780919f42b49a86599f",false);
//
//            CreateOrUpdateBanners create = new CreateOrUpdateBanners();
//            create.setMain(0l,7286601l,"АПИ тестим","Текст","http://select-st.ru","1");
//            create.setContactInfo("Dima","Russia","+495","Moscow","Novaya","12","1", "1","495","89265713850","","SELECT","","","Manana","dfedorovich85@gmail.com",0,6,1,0,9,0,"2354332423542","12","32","22","23","23","43");
//            create.addPhrase(100001l,"Фразочка","No",35.0,35.0,"Yes","Low","12","32");
//            create.addSiteLink("Select-cool","http://select-st.ru/best");
//            create.addSiteLink("Select-coolD","http://select-st.ru/lmao");
//            create.addSiteLink("Select-coolF","http://select-st.ru/lll");
//            create.addMinusKeyword("Манана");
//            YandexDirectPackage getCompaigns = new GetCampaignsList();
//
//            proc.executeMethod(getCompaigns);
//            proc.executeMethod(create);
        }
        catch(Exception ex)
        {
            System.out.println(ex.toString());
        }
    }
}
