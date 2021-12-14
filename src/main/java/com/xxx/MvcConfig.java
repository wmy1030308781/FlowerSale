package com.xxx;

import com.xxx.interceptor.NoLoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//mvc配置类
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Bean//拦截器注入
    public NoLoginInterceptor noLoginInterceptor(){
        return new NoLoginInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(noLoginInterceptor()).addPathPatterns("/**").
                excludePathPatterns("/index","/user/login","/css/**","/images/**","/js/**","/lib/**");
    }
}
