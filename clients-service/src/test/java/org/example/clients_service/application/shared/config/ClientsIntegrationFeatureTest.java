package org.example.clients_service.application.shared.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.example.test_extensions.IntegrationFeatureTest;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@DisableIntegrationAutoConfiguration
@IntegrationFeatureTest
public @interface ClientsIntegrationFeatureTest 
{
    
}
