package cn.edu.cdtu.contactmanagement.config;

import cn.edu.cdtu.contactmanagement.intercept.CrossInterceptor;
import cn.edu.cdtu.contactmanagement.intercept.LoginInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description MVC全局配置
 * @Author makun
 * @Date 2023/5/22 14:50
 * @Version 1.0
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    /**
     * 向容器中添加登录验证切片
     * @return
     */
    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }

    /**
     * 向容器中添加跨域验证切片
     * @return
     */
    @Bean
    public CrossInterceptor crossInterceptor() {
        return new CrossInterceptor();
    }

    /**
     * 登录验证拦截器
     * @param registry
     */
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
