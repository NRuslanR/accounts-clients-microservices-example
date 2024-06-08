package org.example.accounts_view_service.infrastructure.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig 
{
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
        ServerHttpSecurity httpSecurity,
        ReactiveJwtAuthenticationConverter authenticationConverter
    )
    {
        return 
            httpSecurity
                .authorizeExchange(
                    auth -> 
                        auth.anyExchange().authenticated()
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
