package com.dripclothe.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyDataRestConfig {
    @Value("${allowed.origins}")
    private String [] theAllowedOrigins;

    @Bean
    WebMvcConfigurer webMvcConfigurer() {

        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins(theAllowedOrigins)
                        .allowedHeaders("*")
                        .exposedHeaders("Content-Disposition")
                        .allowedMethods("GET","POST","DELETE","PUT", "OPTIONS");
            }
        };

    }
}