package com.josequintero.biblioteca.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.GET, "/libros", "/autores").permitAll()
                .requestMatchers(HttpMethod.GET, "/libros/**", "/autores/**").hasAnyRole("USER", "ADMIN")
                .requestMatchers(HttpMethod.POST, "/libros", "/autores").hasRole("ADMIN")
                .requestMatchers(HttpMethod.DELETE, "/libros/**", "/autores/**").hasRole("ADMIN")
                .anyRequest().denyAll());
        http.httpBasic(Customizer.withDefaults());


        return http.build();
    }
}