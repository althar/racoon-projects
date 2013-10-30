package racoonsoft.store.settings;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import racoonsoft.store.database.PGSQLDataSource;

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

	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        PropertySourcesPlaceholderConfigurer pspc =
                new PropertySourcesPlaceholderConfigurer();
        Resource[] resources = new ClassPathResource[ ]
                { new ClassPathResource( "/racoonsoft/store/properties/main.properties" ) };
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
        result.loadSettings();
        return result;
    }
}
