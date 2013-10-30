package racoonsoft.chaos.settings;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.scheduling.annotation.ScheduledAnnotationBeanPostProcessor;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import racoonsoft.chaos.service.FileHandler;
import racoonsoft.library.processors.FileTransferProcessor;

@Configuration
@Import({WebConfig.class })
public class MainConfig {
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}

	@Bean
	public static ScheduledAnnotationBeanPostProcessor scheduledAnnotationBeanPostProcessor() {
		return new ScheduledAnnotationBeanPostProcessor();
	}

    @Bean
    public static CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
        return resolver;
    }

    @Bean
    public static FileTransferProcessor fileTransferProcessor() throws Exception{
        FileTransferProcessor proc = new FileTransferProcessor(8070,8071,"C:/temp/uploads/books/",new FileHandler());
        return proc;
    }
}
