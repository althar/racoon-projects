package racoonsoft.wish.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import racoonsoft.library.ozon.OzonProcessor;
import racoonsoft.wish.database.PGSQLDataSource;
import racoonsoft.library.payture.PaytureProcessor;

@Configuration
@Import({WebConfig.class })
public class MainConfig {

    @Value("${db.host}")
    private String dbHost;
    @Value("${db.name}")
    private String dbName;
    @Value("${db.login}")
    private String dbLogin;
    @Value("${db.password}")
    private String dbPassword;

    @Value("${ozon.login}")
    private String ozonLogin;
    @Value("${ozon.password}")
    private String ozonPassword;
    @Value("${ozon.apiurl}")
    private String ozonApiUrl;

    @Value("${payture.apihost}")
    private String paytureApiHost;
    @Value("${payture.merchant}")
    private String paytureMerchant;

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer pspc =
                new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[ ]
                { new ClassPathResource( "/racoonsoft/wish/properties/props.properties" ) };
        pspc.setLocations(resources);
        pspc.setIgnoreUnresolvablePlaceholders( true );
        return pspc;
	}

	@Bean
	public ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor() {
		return new ScheduledAnnotationBeanPostProcessor();
	}
    
    @Bean
    public OzonProcessor apiProcessor() {
        return new OzonProcessor(ozonLogin, ozonPassword, ozonApiUrl);
    }

    @Bean
    public PGSQLDataSource pgsqlDataSource() throws Exception{
        System.out.println("Get data source - start: "+dbHost);
        PGSQLDataSource result = new PGSQLDataSource(dbHost,dbName,5432,dbLogin,dbPassword,"org.postgresql.Driver","jdbc:postgresql:");
        System.out.println("Get data source - success");
        result.loadSettings();
        return result;
    }
    @Bean
    public PaytureProcessor paytureProcessor()
    {
        PaytureProcessor proc = new PaytureProcessor(paytureApiHost,paytureMerchant);
        return proc;
    }
}
