package com.swpu.logging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    @Bean
    public CorsFilter corsFilter(){
        CorsConfiguration conf=new CorsConfiguration();
        //允许那些请救原跨域访问，比如lh8080；*允许所有跨域
        conf.addAllowedOriginPattern("*");
        //允许所有请求头跨域
        conf.addAllowedHeader("*");
        //允许所有请求方式跨域例如post
        conf.addAllowedMethod("*");
        //允许前端请求携带认证信息，如前端打开了后端也要打开
        conf.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",conf);
        return new CorsFilter(source);
    }
}
