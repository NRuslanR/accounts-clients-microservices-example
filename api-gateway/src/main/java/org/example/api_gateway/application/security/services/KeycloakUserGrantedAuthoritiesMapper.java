package org.example.api_gateway.application.security.services;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.authority.mapping.GrantedAuthoritiesMapper;
import org.springframework.security.oauth2.core.oidc.user.OidcUserAuthority;

public class KeycloakUserGrantedAuthoritiesMapper implements GrantedAuthoritiesMapper 
{
    @Override
    public Collection<? extends GrantedAuthority> mapAuthorities(Collection<? extends GrantedAuthority> authorities) 
    {
        Set<GrantedAuthority> userAuthorities = new HashSet<>();

        authorities.forEach(authority -> {

            if (!(authority instanceof OidcUserAuthority))
                return;

            var userInfo = ((OidcUserAuthority)authority).getUserInfo();

            if (!userInfo.hasClaim("realm_access"))
                return;

            Map<String, Collection<String>> realm_access = userInfo.getClaim("realm_access");

            if (!realm_access.containsKey("roles"))
                return;

            userAuthorities.addAll(
                realm_access
                    .get("roles")
                    .stream()
                    .map(SimpleGrantedAuthority::new)
                    .toList()
            );
        });

        return userAuthorities;
    }
    
}
