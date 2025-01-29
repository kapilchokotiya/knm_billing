package com.knm.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class CorsConfiguration {

    @Bean
    public CorsFilter corsFilter() {
        CorsConfigurationSource source = request -> {
            CorsConfiguration config = new CorsConfiguration();
            config.addAllowedOrigin("*"); // Adjust to your requirements
            config.addAllowedMethod("*");
            config.addAllowedHeader("*");
            return null;
        };
        return new CorsFilter();
    }

    private void addAllowedHeader(String s) {
    }

    private void addAllowedMethod(String s) {
    }

    private void addAllowedOrigin(String s) {
    }
}
