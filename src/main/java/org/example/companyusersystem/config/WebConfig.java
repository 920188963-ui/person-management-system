package org.example.companyusersystem.config;

import org.example.companyusersystem.interceptor.AdminInterceptor;
import org.example.companyusersystem.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//标志配置类
public class WebConfig implements WebMvcConfigurer {
    //通过重写自定义配置
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截
        registry.addInterceptor(new LoginInterceptor())
                //拦截所有接口
                .addPathPatterns("/api/**")
                //排除登录与注册
                .excludePathPatterns("/api/login","/api/register");
        registry.addInterceptor(new AdminInterceptor())
                .addPathPatterns("/api/employee/add","/api/employee/update","/api/employee/delete","/api/employee/batchDelete","/api/employee/upload");
    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry){
        //addResourceHandler 资源处理器
        registry.addResourceHandler("/uploads/**")
                //设置真实资源位置
                //file:uploads = 服务器本地的uploads文件夹
                .addResourceLocations("file:uploads/");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/login.html");
        registry.addRedirectViewController("/login", "/login.html");
        registry.addRedirectViewController("/register", "/register.html");
        registry.addRedirectViewController("/index", "/index.html");
        registry.addRedirectViewController("/detail", "/detail.html");
    }
}
