package org.example.clients_service.application.shared.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.example.test_extensions.UnitApiTest;

@UnitApiTest
@DisableUnitAutoConfiguration
@Retention(RetentionPolicy.RUNTIME)
public @interface ClientsUnitApiTest 
{
    
}