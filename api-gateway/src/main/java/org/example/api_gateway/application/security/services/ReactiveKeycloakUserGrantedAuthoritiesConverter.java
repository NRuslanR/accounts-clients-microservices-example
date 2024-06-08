package org.example.api_gateway.application.security.services;

import java.util.Collection;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

import reactor.core.publisher.Flux;

public class ReactiveKeycloakUserGrantedAuthoritiesConverter implements Converter<Jwt, Flux<GrantedAuthority>>
{
    @Override
    @Nullable
    public Flux<GrantedAuthority> convert(@SuppressWarnings("null") Jwt source) 
    {
        if (!source.hasClaim("realm_access"))
            return Flux.empty();

        Map<String, Collection<String>> realm_access = source.getClaim("realm_access");
        
        if (!realm_access.containsKey("roles"))
            return Flux.empty();

        var authorities =
            realm_access
                .get("roles")
                .stream()
                .map(role -> (GrantedAuthority)new SimpleGrantedAuthority(role))
                .toList();

        return Flux.fromIterable(authorities);
    }
}
