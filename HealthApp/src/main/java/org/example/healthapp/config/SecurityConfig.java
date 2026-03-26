package org.example.healthapp.config;

import lombok.RequiredArgsConstructor;
import org.example.healthapp.security.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    // Injectăm filtrul (paznicul) pe care tocmai l-ai creat
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                // Permitem afișarea consolei H2 în browser
                .headers(headers -> headers.frameOptions(frameOptions -> frameOptions.disable()))

                // Aici definim REGULILE DE ACCES:
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/auth/login").permitAll() // 🔓 Login-ul este public (altfel cum am lua token-ul?)
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/api/v1/medici").permitAll()// 🔓 Consola bazei de date e publică pentru testare
                        .requestMatchers("/api/v1/trasabilitate").permitAll() // 🔓 Lăsăm ușa deschisă pentru senzorul ESP8266 să trimită date
                        .anyRequest().authenticated()                      // 🔒 ABSOLUT TOATE celelalte rute necesită Token JWT!
                )

                // Îi spunem Spring-ului să nu folosească Sesiuni clasice, ci să fie "Stateless" (doar pe bază de token)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // Punem paznicul nostru (jwtAuthFilter) înainte de filtrul standard al Spring-ului
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}