package com.bs.boot.myboardrest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    // cors 설정하기
    //REDIS 설정하기
    CorsConfiguration corsConfiguration = new CorsConfiguration();
}
