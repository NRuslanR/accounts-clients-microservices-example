package org.example.api_gateway.application.security.config;

import org.example.api_gateway.application.security.services.KeycloakUserGrantedAuthoritiesMapper;
import org.example.api_gateway.application.security.services.ReactiveKeycloakUserGrantedAuthoritiesConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;
import org.springframework.security.web.server.SecurityWebFilterChain;

import lombok.extern.slf4j.Slf4j;

@Configuration
@EnableWebFluxSecurity
@Slf4j
public class SecurityConfig 
{
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity)
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
                            jwt -> Customizer.withDefaults()
                        )
                )
                .oauth2Login(
                    auth -> Customizer.withDefaults()
                )
                .csrf(c -> c.disable())
                .build();
    }

    @Bean
    public GrantedAuthoritiesMapper grantedAuthoritiesMapper()
    {
        return new KeycloakUserGrantedAuthoritiesMapper();
    }
    
    @Bean
    public ReactiveJwtAuthenticationConverter jwtAuthenticationConverter()
    {
        var jwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
            keycloakUserGrantedAuthoritiesConverter()
        );
        
        return jwtAuthenticationConverter;
    }

    @Bean
    public ReactiveKeycloakUserGrantedAuthoritiesConverter keycloakUserGrantedAuthoritiesConverter()
    {
        return new ReactiveKeycloakUserGrantedAuthoritiesConverter();
    }
}
