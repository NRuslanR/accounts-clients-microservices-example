package org.example.keycloak_roles_converters;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Import;

@AutoConfiguration
@ConditionalOnClass({KeycloakUserGrantedAuthoritiesConverterConfig.class})
@Import({KeycloakUserGrantedAuthoritiesConverterConfig.class})
public class KeycloakUserGrantedAuthoritiesConverterAutoConfig 
{
    
}
