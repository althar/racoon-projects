package racoonsoft.mfaoverlord.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import racoonsoft.mfaoverlord.structure.HistoryItem;
import racoonsoft.racoonspring.data.database.DatabaseProcessor;
import racoonsoft.racoonspring.mvc.interceptor.HistoryInterceptor;

@Configuration
@Import({WebConfig.class })
@ComponentScan(basePackages = "racoonsoft.mfaoverlord")
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

    @Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer()
    {
        PropertySourcesPlaceholderConfigurer pspc =
                new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[ ]
                { new ClassPathResource("/racoonsoft/mfaoverlord/properties/main.properties") };
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
    public DatabaseProcessor pgsqlDataSource()
    {
        try
        {
            System.out.println("Get data source - start: "+dbHost);
            DatabaseProcessor result = new DatabaseProcessor(dbHost,dbName,5432,dbLogin,dbPassword,"org.postgresql.Driver","jdbc:postgresql:");
            result.connect();
            System.out.println("Get data source - success");
            return result;
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
            return null;
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        HistoryInterceptor history = new HistoryInterceptor(HistoryItem.class);
        InterceptorRegistration historyInterceptor = registry.addInterceptor(history);
        historyInterceptor.addPathPatterns("/**");
        history.dbProc = pgsqlDataSource();
    }
}
