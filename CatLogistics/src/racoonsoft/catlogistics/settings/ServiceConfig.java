package racoonsoft.catlogistics.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;


@Configuration
@ComponentScan({"erdicto.common.service","erdicto.fin.admin.service"})
@EnableTransactionManagement(proxyTargetClass = true)
public class ServiceConfig {


}
