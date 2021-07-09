package com.heaven.bookstore.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**","/images","/css","/js")
                .addResourceLocations("classpath:/static/","classpath:/images/","classpath:/css/","classpath:/js/","classpath:/templates/","classpath:/images/books/")
                .setCachePeriod(0);
    }
}
