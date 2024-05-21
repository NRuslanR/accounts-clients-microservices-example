package org.example.accounts_service.application.shared.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.TestPropertySource;

@Target(ElementType.TYPE)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Import(IntegrationFeatureTestsConfig.class)
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = "(.*)integration(.*)")
@TestPropertySource(properties = "spring.config.location=classpath:application-integration-test.yml")
public @interface IntegrationFeatureTest 
{
    
}
