package com.jota_nunes_back_end.jotanunes.infra.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityFilter {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable() // desativa CSRF para testes
                .authorizeHttpRequests(authorize -> authorize
                        .anyRequest().permitAll() // permite qualquer requisição
                );
        return http.build();
    }
}
