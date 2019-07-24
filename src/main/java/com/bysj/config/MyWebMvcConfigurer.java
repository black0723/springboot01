package com.bysj.config;

import com.bysj.servletcomponent.MyInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;
import java.io.IOException;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
        // eg http://localhost:9099/upload-files/111.jpg
        registry.addResourceHandler("/upload-files/**").addResourceLocations("file:D:/upload_files/");
        //默认写在后面 eg http://localhost:9099/upload-files/0.jpg
        registry.addResourceHandler("/upload-files/**").addResourceLocations("classpath:/upload_files/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/toLogin", "/login");
    }
}
