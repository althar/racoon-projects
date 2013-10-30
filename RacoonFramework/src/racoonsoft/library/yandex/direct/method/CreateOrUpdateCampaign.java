package racoonsoft.library.yandex.direct.method;

import racoonsoft.library.helper.TypeHelper;
import racoonsoft.library.json.JSONProcessor;
import racoonsoft.library.yandex.direct.YandexDirectPackage;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

// TODO: Set default values...
public class CreateOrUpdateCampaign extends YandexDirectPackage
{
    // Main
    private Long campaignID;
    private String name;
    private String fio;
    private String startDate;
    public void setMain(Long campaignID,String name,String fio,Date startDate)
    {
        this.campaignID = campaignID;
        this.name = name;
        this.fio = fio;
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.startDate = formatter.format(startDate);
    }

    // Strategy
    private HashMap<String,Object> strategy = new HashMap<String, Object>();
    public void setStrategy(String strategyName, Double strategyMaxPrice, Double strategyAveragePrice, Double strategyWeeklySumLimit, Long strategyClicksPerWeek)
    {
        strategy = new HashMap<String, Object>();
        strategy.put("strategyName",strategyName);
        strategy.put("strategyMaxPrice",strategyMaxPrice);
        strategy.put("strategyAveragePrice",strategyAveragePrice);
        strategy.put("strategyWeeklySumLimit",strategyWeeklySumLimit);
        strategy.put("strategyClicksPerWeek",strategyClicksPerWeek);
    }

    // Sms notification
    private HashMap<String,Object> smsNotification = new HashMap<String, Object>();
    public void setSmsNotification(String smsNotificationMetricaSms, String smsNotificationModerateResultSms, String smsNotificationMoneyInSms, String smsNotificationMoneyOutSms, String smsNotificationSmsTimeFrom, String smsNotificationSmsTimeTo)
    {
        smsNotification = new HashMap<String, Object>();
        smsNotification.put("smsNotificationMetricaSms",smsNotificationMetricaSms);
        smsNotification.put("smsNotificationModerateResultSms",smsNotificationModerateResultSms);
        smsNotification.put("smsNotificationMoneyInSms",smsNotificationMoneyInSms);
        smsNotification.put("smsNotificationMoneyOutSms",smsNotificationMoneyOutSms);
        smsNotification.put("smsNotificationSmsTimeFrom",smsNotificationSmsTimeFrom);
        smsNotification.put("smsNotificationSmsTimeTo",smsNotificationSmsTimeTo);
    }

    // EmailNotification
    private HashMap<String,Object> emailNotification = new HashMap<String, Object>();
    public void setEmailNotification(String emailNotificationEmail, String emailNotificationWarnPlaceInterval, String emailNotificationMoneyWarningValue, String emailNotificationSendAccNews, String emailNotificationSendWarn)
    {
        emailNotification = new HashMap<String, Object>();
        emailNotification.put("emailNotificationEmail",emailNotificationEmail);
        emailNotification.put("emailNotificationWarnPlaceInterval",emailNotificationWarnPlaceInterval);
        emailNotification.put("emailNotificationMoneyWarningValue",emailNotificationMoneyWarningValue);
        emailNotification.put("emailNotificationSendAccNews",emailNotificationSendAccNews);
        emailNotification.put("emailNotificationSendWarn",emailNotificationSendWarn);
    }

    // Time target
    private HashMap<String,Object> timeTarget = new HashMap<String, Object>();
    public void setTarget(String timeTargetShowOnHolidays, Long timeTargetHolidayShowFrom, Long timeTargetHolidayShowTo, String timeTargetTimeZone, ArrayList<Integer> timeTargetDays, ArrayList<Integer> timeTargetHours)
    {
        timeTarget.put("timeTargetShowOnHolidays",timeTargetShowOnHolidays);
        timeTarget.put("timeTargetHolidayShowFrom",timeTargetHolidayShowFrom);
        timeTarget.put("timeTargetHolidayShowTo",timeTargetHolidayShowTo);
        timeTarget.put("timeTargetTimeZone",timeTargetTimeZone);
        ArrayList<HashMap<String,ArrayList<Integer>>> daysHours = new ArrayList<HashMap<String, ArrayList<Integer>>>();
        HashMap<String,ArrayList<Integer>> timeTargetItem = new HashMap<String, ArrayList<Integer>>();
        timeTargetItem.put("hours",timeTargetHours);
        timeTargetItem.put("days",timeTargetDays);
        daysHours.add(timeTargetItem);
        timeTarget.put("daysHours",daysHours);
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

    // Other
    private HashMap<String,Object> others = new HashMap<String, Object>();
    public void setOthers(String statusContextStop
        ,String contextLimit
        ,Long contextLimitSum
        ,Long contextPricePercent
        ,String autoOptimization
        ,String statusMetricaControl
        ,String disabledDomains
        ,String disabledIps
        ,String statusOpenStat
        ,String considerTimeTarget
        ,String addRelevantPhrases
        ,Long relevantPhrasesBudgetLimit
        ,ArrayList<String> minusKeywords)
    {
        others = new HashMap<String, Object>();
        others.put("statusContextStop",statusContextStop);
        others.put("contextLimit",contextLimit);
        others.put("contextLimitSum",contextLimitSum);
        others.put("contextPricePercent",contextPricePercent);
        others.put("autoOptimization",autoOptimization);
        others.put("statusMetricaControl",statusMetricaControl);
        others.put("disabledDomains",disabledDomains);
        others.put("disabledIps",disabledIps);
        others.put("statusOpenStat",statusOpenStat);
        others.put("considerTimeTarget",considerTimeTarget);
        others.put("addRelevantPhrases",addRelevantPhrases);
        others.put("relevantPhrasesBudgetLimit",relevantPhrasesBudgetLimit);
        others.put("minusKeywords",minusKeywords);
    }


    @Override
    public JSONProcessor getJsonPackage(String application_id, String login, String access_token) throws Exception
    {
        pack = new JSONProcessor(new HashMap<String, Object>());
        HashMap<String,Object> struct = pack.getStructure();
        struct.put("method","GetCampaignsList");
        struct.put("login",login);
        struct.put("token",access_token);
        struct.put("application_id", application_id);

        // Main
        struct.put("CampaignID",campaignID);
        struct.put("Name",name);
        struct.put("FIO",fio);
        struct.put("StartDate",startDate);

        // Strategy
        struct.put("strategy",strategy);

        // Sms notification
        struct.put("smsNotification",smsNotification);

        // Email notification
        struct.put("emailNotification",emailNotification);

        // Time target
        struct.put("timeTarget",timeTarget);

        // Minus keywords
        struct.put("minusKeywords",minusKeywords);

        // Others
        struct = TypeHelper.addHashMap(others,struct,true);
        pack = new JSONProcessor(struct);
        return pack;
    }
}
