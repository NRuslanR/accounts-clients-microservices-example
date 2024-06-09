package org.example.accounts_service.application.shared.infrastructure.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import lombok.SneakyThrows;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig 
{
    @SneakyThrows
    @Bean
    public SecurityFilterChain securityFilterChain(
        HttpSecurity httpSecurity,
        JwtAuthenticationConverter authenticationConverter
    )
    {
        return 
            httpSecurity
                .authorizeHttpRequests(
                    auth -> auth.anyRequest().authenticated()
                )
                .oauth2ResourceServer(
                    auth -> 
                        auth.jwt(
                            jwt -> jwt.jwtAuthenticationConverter(authenticationConverter)
                        )
                )
                .build();
    }
}
