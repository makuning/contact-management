package cn.edu.cdtu.contactmanagement.config;

import cn.edu.cdtu.contactmanagement.intercept.CrossInterceptor;
import cn.edu.cdtu.contactmanagement.intercept.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    @Bean
    public CrossInterceptor crossInterceptor() {
        return new CrossInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(crossInterceptor())
                .addPathPatterns("/**");

        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/api/v1/pri/**")
                .excludePathPatterns("/api/v1/pri/user/register","/api/v1/pri/user/login");
        WebMvcConfigurer.super.addInterceptors(registry);
    }
}
