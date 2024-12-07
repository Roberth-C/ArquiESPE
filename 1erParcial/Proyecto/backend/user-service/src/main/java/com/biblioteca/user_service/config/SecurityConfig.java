package com.biblioteca.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Deshabilitar CSRF si no es necesario
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/users/**").permitAll() // Permitir acceso público a esta ruta
                .anyRequest().authenticated() // Cualquier otra solicitud debe estar autenticada
            );
        return http.build();
    }
}
