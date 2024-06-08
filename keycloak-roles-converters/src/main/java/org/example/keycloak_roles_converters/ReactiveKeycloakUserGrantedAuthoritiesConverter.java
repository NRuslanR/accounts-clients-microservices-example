package org.example.keycloak_roles_converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;

@RequiredArgsConstructor
public class ReactiveKeycloakUserGrantedAuthoritiesConverter implements Converter<Jwt, Flux<GrantedAuthority>>
{
    private final KeycloakUserGrantedAuthoritiesConverter converter;

    @Override
    public Flux<GrantedAuthority> convert(@SuppressWarnings("null") Jwt source) 
    {
        return Flux.fromIterable(converter.convert(source)  );
    }
}