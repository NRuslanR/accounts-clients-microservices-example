package org.example.clients_service.application.shared.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.example.test_extensions.UnitFeatureTest;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@DisableUnitAutoConfiguration
@UnitFeatureTest
public @interface ClientsUnitFeatureTest 
{
    
}
