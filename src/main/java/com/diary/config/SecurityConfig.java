package com.diary.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HttpBasicConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration  // 설정파일이라고 명시
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(HttpBasicConfigurer::disable)    // http 기본인증 비활성화(기본인증은 보안이 약함) 이부분 확실히 모르겠음
                .csrf(CsrfConfigurer::disable)  // csrf 보호 비활성화 (Cross Site Request Forgery 공격)
                .cors(Customizer.withDefaults())    // cors 기본설정(외부에서 다른 사용자가 요청하는것)
                .sessionManagement(configurer -> configurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // 세선고정보호?
                .authorizeRequests( // 특정 url 에대한 권한 설정
                        authorize -> authorize
                                .requestMatchers("/swagger-ui/**","/user/**").permitAll()); // 모두 허용
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
