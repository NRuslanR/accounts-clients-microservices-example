package org.example.keycloak_roles_converters;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverter;

@Configuration
public class KeycloakUserGrantedAuthoritiesConverterConfig 
{
    @Bean
    public JwtAuthenticationConverter jwtAuthenticationConverter()
    {
        var jwtAuthenticationConverter = new JwtAuthenticationConverter();

        jwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
            keycloakUserGrantedAuthoritiesConverter()
        );

        return jwtAuthenticationConverter;
    }

    @Bean
    public ReactiveJwtAuthenticationConverter reactiveJwtAuthenticationConverter()
    {
        var reactiveJwtAuthenticationConverter = new ReactiveJwtAuthenticationConverter();

        reactiveJwtAuthenticationConverter.setJwtGrantedAuthoritiesConverter(
            reactiveKeycloakUserGrantedAuthoritiesConverter()
        );

        return reactiveJwtAuthenticationConverter;
    }
    
    @Bean
    public ReactiveKeycloakUserGrantedAuthoritiesConverter reactiveKeycloakUserGrantedAuthoritiesConverter()
    {
        return new ReactiveKeycloakUserGrantedAuthoritiesConverter(keycloakUserGrantedAuthoritiesConverter());
    }

    @Bean
    public KeycloakUserGrantedAuthoritiesConverter keycloakUserGrantedAuthoritiesConverter()
    {
        return new KeycloakUserGrantedAuthoritiesConverter();
    }
}
