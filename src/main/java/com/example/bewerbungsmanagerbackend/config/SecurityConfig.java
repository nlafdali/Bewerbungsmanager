package com.example.bewerbungsmanagerbackend.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // CSRF aus für REST
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().permitAll()   // alles erlauben
                );
        return http.build();
    }


}
