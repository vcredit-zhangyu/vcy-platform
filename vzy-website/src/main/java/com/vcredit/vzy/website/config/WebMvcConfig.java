package com.vcredit.vzy.website.config;

import com.vcredit.vzy.website.interceptor.CookieResolveInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * description :
 *
 * @author : zhanghui
 * @date : 2024/3/18
 */
@Configuration
@RequiredArgsConstructor
public class WebMvcConfig implements WebMvcConfigurer {

    @Value("${security.oauth2.client.permit-urls}")
    private String excludePaterns;

    private final CookieResolveInterceptor userAuthInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(userAuthInterceptor).addPathPatterns("/**")
            .excludePathPatterns("/webjars/**", "/swagger-resources/**", "/doc.html/**", "/logout", "/login", "/actuator/**", "/error")
            .excludePathPatterns(excludePaterns.split(","));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //swagger-bootstrap-ui
        registry.addResourceHandler("doc.html")
            .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
            .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOriginPatterns("*")
            .allowCredentials(true)
            .allowedMethods("GET", "POST", "DELETE", "PUT", "PATCH")
            .maxAge(3600);
    }
}
