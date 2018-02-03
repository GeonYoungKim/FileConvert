package skuniv.ac.kr;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;


public class AppInitializer {

    private final String MAPPING_URL = "/";
    private final String CONFIG_LOCATION = "skuniv.ac.*";
    
    
    private AnnotationConfigWebApplicationContext getContext() {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.setConfigLocation(CONFIG_LOCATION);
        return context;
    }

    // https://github.com/ralscha/extdirectspring/wiki/Config-Spring-Java-Config
    private MultipartConfigElement getMultiPartConfigElement() {                  //TODO Step 2
        return new MultipartConfigElement("", 2000000, 10000000, 0);
    }	

}