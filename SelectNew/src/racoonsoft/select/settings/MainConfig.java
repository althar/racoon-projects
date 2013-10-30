package racoonsoft.select.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import racoonsoft.library.access.UserProcessor;
import racoonsoft.library.sms.SMSProcessor;
import racoonsoft.select.database.PGSQLDataSource;
import racoonsoft.select.interceptor.AccessInterceptor;
import racoonsoft.select.interceptor.HistoryInterceptor;
import racoonsoft.select.service.AerseService;
import racoonsoft.select.structure.Basket;
import racoonsoft.select.structure.BasketStorage;

@Configuration
@Import({WebConfig.class })
@ComponentScan(basePackages = "racoonsoft.select")
public class MainConfig extends WebMvcConfigurerAdapter {

    @Value("${db.host}")
    private String dbHost;
    @Value("${db.name}")
    private String dbName;
    @Value("${db.login}")
    private String dbLogin;
    @Value("${db.password}")
    private String dbPassword;

    @Value("${aerse.pop}")
    private String aersePop;
    @Value("${aerse.lang}")
    private String aerseLang;
    @Value("${aerse.key}")
    private String aerseKey;

    @Value("${sms.login}")
    private String smsLogin;
    @Value("${sms.password}")
    private String smsPassword;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer pspc =
                new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[ ]
                { new ClassPathResource( "/racoonsoft/select/properties/main.properties" ) };
        pspc.setLocations(resources);
        pspc.setIgnoreUnresolvablePlaceholders( true );
        return pspc;
	}

	@Bean
	public ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor() {
		return new ScheduledAnnotationBeanPostProcessor();
	}
    
    @Bean
    public PGSQLDataSource pgsqlDataSource() throws Exception{
        System.out.println("Get data source - start: "+dbHost);
        PGSQLDataSource result = new PGSQLDataSource(dbHost,dbName,5432,dbLogin,dbPassword,"org.postgresql.Driver","jdbc:postgresql:");
        System.out.println("Get data source - success");
        return result;
    }

    @Bean
    public BasketStorage basketStorage() throws Exception
    {
        UserProcessor.setUserTableName("user_list");
        BasketStorage.dbProc = pgsqlDataSource();
        return new BasketStorage();
    }

    @Bean
    public AerseService aerseService()
    {
        return new AerseService(aersePop,aerseKey,aerseLang);
    }

    @Bean
    public SMSProcessor smsService()
    {
        return new SMSProcessor(smsLogin,smsPassword);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        AccessInterceptor access = new AccessInterceptor();
        HistoryInterceptor history = new HistoryInterceptor();
        try
        {
            PGSQLDataSource source = pgsqlDataSource();
            history.setDbProc(source);
            access.setDbProc(source);
        }
        catch(Exception ex)
        {
        }
        registry.addInterceptor(history).addPathPatterns("/**");
        registry.addInterceptor(access).addPathPatterns("/**");
    }
}
