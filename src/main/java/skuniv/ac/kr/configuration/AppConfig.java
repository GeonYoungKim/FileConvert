package skuniv.ac.kr.configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
@ComponentScan("skuniv.ac.*")
public class AppConfig {
    @Bean
    public CommonsMultipartResolver multipartResolver() {  //TODO Step 2
        CommonsMultipartResolver resolver=new CommonsMultipartResolver();
        resolver.setDefaultEncoding("UTF-8");
       
        return resolver;
    }
}