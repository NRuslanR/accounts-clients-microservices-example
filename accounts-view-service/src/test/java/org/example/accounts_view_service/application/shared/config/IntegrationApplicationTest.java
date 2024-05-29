package org.example.accounts_view_service.application.shared.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.example.test_extensions.IntegrationFeatureTest;
import org.testcontainers.junit.jupiter.Testcontainers;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@IntegrationFeatureTest
@Testcontainers
@DisableMessagingAutoConfiguration
public @interface IntegrationApplicationTest {
    
}
