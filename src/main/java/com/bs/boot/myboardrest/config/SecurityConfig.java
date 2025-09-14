package com.bs.boot.myboardrest.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    @Primary
    public CorsConfigurationSource CorsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOriginPatterns(List.of(
                "/*"
        ));
        // 모든 도메인(origin)에서 오는 요청을 허용한다는 의미
        corsConfiguration.setAllowedMethods(List.of("GET","POST","PUT","PATCH","DELETE","OPTIONS","HEAD"));
        // 허용할 Http 메소드를 입력해준다.
//        corsConfiguration.setAllowedHeaders(List.of("Authorization","Content-Type"));
        // 브라우저 요청 해더에 보내도 되는 것 Authorization -> JWT / JSON -> Content-Type
        corsConfiguration.setAllowedHeaders(List.of("*"));
        corsConfiguration.setExposedHeaders(List.of("*"));

//        corsConfiguration.setExposedHeaders(List.of("Authorization")); -> Refresh Token에서 필요없음
        // 응답 해더 중 JS 코드 볼 수 있게 해주는 것

        corsConfiguration.setAllowCredentials(true);
        // refresh 토큰을 같이 사용하는 경우 HttpOnly-> 쿠키이므로 허용해야지 사용이 가능함
        // 모두 헤더/ 바디로 넣어서 할 수 있는 방식도 존재 한다.
        corsConfiguration.setMaxAge(3600L);


        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }

}
