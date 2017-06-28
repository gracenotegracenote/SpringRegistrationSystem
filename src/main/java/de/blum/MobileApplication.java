package de.blum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@SpringBootApplication
public class MobileApplication {
    
    public static void main(String[] args) {
        SpringApplication.run(MobileApplication.class, args);
    }
    
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry.addResourceHandler("/public/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        }  
    }
}
