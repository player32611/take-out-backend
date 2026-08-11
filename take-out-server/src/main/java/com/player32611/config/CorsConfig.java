package com.player32611.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.List;


@Configuration
public class CorsConfig {


    @Bean
    public CorsFilter corsFilter() {

        CorsConfiguration config = new CorsConfiguration();


        // 允许前端地址
        config.setAllowedOrigins(
                List.of("http://localhost:3000")
        );


        // 允许请求方法
        config.setAllowedMethods(
                List.of(
                        "GET",
                        "POST",
                        "PUT",
                        "DELETE",
                        "OPTIONS"
                )
        );


        // 允许请求头
        config.setAllowedHeaders(
                List.of("*")
        );


        // 是否允许携带 Cookie
        config.setAllowCredentials(true);


        UrlBasedCorsConfigurationSource source =
                new UrlBasedCorsConfigurationSource();


        source.registerCorsConfiguration(
                "/**",
                config
        );


        return new CorsFilter(source);
    }
}