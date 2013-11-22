package racoonsoft.languagebox.settings;

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
import racoonsoft.languagebox.database.PostgresqlDataSource;
import racoonsoft.languagebox.interceptor.AccessInterceptor;
import racoonsoft.languagebox.interceptor.HistoryInterceptor;
import racoonsoft.library.sms.SMSProcessor;

@Configuration
@Import({WebConfig.class })
@ComponentScan(basePackages = "racoonsoft.languagebox")
public class MainConfig extends WebMvcConfigurerAdapter
{
    @Value("${db.host}")
    private String dbHost;
    @Value("${db.name}")
    private String dbName;
    @Value("${db.login}")
    private String dbLogin;
    @Value("${db.password}")
    private String dbPassword;

    @Value("${sms.login}")
    private String smsLogin;
    @Value("${sms.password}")
    private String smsPassword;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        PropertySourcesPlaceholderConfigurer pspc =
                new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[ ]
                { new ClassPathResource( "/racoonsoft/languagebox/properties/main.properties" ) };
        pspc.setLocations(resources);
        pspc.setIgnoreUnresolvablePlaceholders( true );
        return pspc;
	}

	@Bean
	public ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor()
    {
		return new ScheduledAnnotationBeanPostProcessor();
	}
    
    @Bean
    public PostgresqlDataSource pgsqlDataSource() throws Exception
    {
        System.out.println("Get data source - start: "+dbHost);
        PostgresqlDataSource result = new PostgresqlDataSource(dbHost,dbName,5432,dbLogin,dbPassword,"org.postgresql.Driver","jdbc:postgresql:");
        System.out.println("Get data source - success");
        return result;
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
        registry.addInterceptor(history).addPathPatterns("/**");
        registry.addInterceptor(access).addPathPatterns("/**");
    }
}
