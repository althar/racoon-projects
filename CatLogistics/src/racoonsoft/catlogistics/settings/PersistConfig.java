package racoonsoft.catlogistics.settings;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaDialect;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.HashMap;

/**
 * @author Alexander Severin
 */
@Configuration
@PropertySource("classpath:config-${env:dev}.properties")
@ComponentScan(basePackages = {"erdicto.common.persist","erdicto.common.admin.entity", "erdicto.fin.admin.persist"})
@EnableTransactionManagement
public class PersistConfig {
	@Autowired
    Environment env;

	@Value("${persist.tenant.url}")
    String tenantUrl;
	
	@Value("${persist.tenant.username}")
	String tenantUser;
	
	@Value("${persist.tenant.password}")
	String tenantPwd;
	
	@Value("${persist.tenant.hbm2ddl}")
	String tenantHbm2ddl;
	
	
	@Value("${persist.pool.maxWait}")
	long poolMaxWait;
	
	@Value("${persist.pool.minIdle}")
	int poolMinIdle;
	
	@Value("${persist.pool.maxActive}")
	int poolMaxActive;
	
	@Value("${persist.pool.initialSize}")
	int poolInitialSize;
	
	@Value("${persist.pool.timeBtwEvictionRuns}")
	long timeBetweenEvictionRuns;
	
	@Value("${persist.tenant.packagesToScan:erdicto.fin.admin.entity}")
	private String[] packagesToScan;
	
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer(){
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	@Bean
	public PersistenceExceptionTranslationPostProcessor persistenceExceptionTranslationPostProcessor(){
		return new PersistenceExceptionTranslationPostProcessor();
	}	
}
