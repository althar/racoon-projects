package racoonsoft.catlogistics.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.security.web.access.expression.DefaultWebSecurityExpressionHandler;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author Alexander Severin
 */
@Configuration
@EnableWebMvc
@ComponentScan({"erdicto.common.controller", "erdicto.fin.admin.controller"})
@PropertySource("classpath:config-${env}.properties")
@EnableTransactionManagement(proxyTargetClass = true)
public class WebConfig extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		super.addResourceHandlers(registry);
		registry.addResourceHandler("/js/**").addResourceLocations("/js/");
		registry.addResourceHandler("/css/**").addResourceLocations("/css/");
		registry.addResourceHandler("/img/**").addResourceLocations("/img/");
	}

	@Override
	public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
		exceptionResolvers.add(new HandlerExceptionResolver() {
            @Override
            public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
                return null;  //To change body of implemented methods use File | Settings | File Templates.
            }
        });
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver result = new InternalResourceViewResolver();
		result.setSuffix(".jsp");
		result.setPrefix("/WEB-INF/jsp/");
		return result;
	}
	
	@Bean
	public LocaleResolver localeResolver() {
		return new FixedLocaleResolver();
	}

	@Bean
	public DefaultWebSecurityExpressionHandler getDefaultWebSecurityExpressionHandler(){
		return new DefaultWebSecurityExpressionHandler();
	}

}
