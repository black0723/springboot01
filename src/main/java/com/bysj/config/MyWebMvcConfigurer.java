package com.bysj.config;

import com.bysj.servletcomponent.MyInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Value("${customize.upload-path}")
    private String UPLOAD_PATH;  //"D:/upload_files/"

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //配置server虚拟路径，handler为前台访问的目录，locations为files相对应的本地路径
        // eg http://localhost:9099/upload-files/111.jpg
        registry.addResourceHandler("/upload-files/**")
                .addResourceLocations("classpath:/upload_files/", "file:" + UPLOAD_PATH);
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        //registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/admin/**", "/client/cart/**", "/client/order/**")
                .excludePathPatterns(/*"/static/**",*/ "/login", "/logout", "/register");
    }
}
