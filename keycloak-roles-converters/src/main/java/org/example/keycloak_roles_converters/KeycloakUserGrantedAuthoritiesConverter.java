package org.example.keycloak_roles_converters;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeycloakUserGrantedAuthoritiesConverter implements Converter<Jwt, Collection<GrantedAuthority>> 
{
    @Override
    @Nullable
    public Collection<GrantedAuthority> convert(Jwt source) 
    {
        if (!source.hasClaim("realm_access"))
            return Collections.emptyList();

        Map<String, Collection<String>> realm_access = source.getClaim("realm_access");
        
        if (!realm_access.containsKey("roles"))
            return Collections.emptySet();

        var authorities =
            realm_access
                .get("roles")
                .stream()
                .map(role -> (GrantedAuthority)new SimpleGrantedAuthority(role))
                .toList();

        return authorities;
    }
}
