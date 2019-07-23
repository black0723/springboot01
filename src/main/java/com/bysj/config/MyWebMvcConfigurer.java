package com.bysj.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        /*Resource resource = new ClassPathResource("upload_files");
        try {
            File sourceFile =  resource.getFile();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        registry.addResourceHandler("/upload-files/**").addResourceLocations("file:/D:/upload_files/");
    }
}
