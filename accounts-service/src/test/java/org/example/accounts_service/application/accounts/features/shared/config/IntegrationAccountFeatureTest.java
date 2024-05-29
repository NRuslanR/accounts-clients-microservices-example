package org.example.accounts_service.application.accounts.features.shared.config;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.example.test_extensions.IntegrationFeatureTest;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@IntegrationFeatureTest
@Inherited
@Import(IntegrationAccountFeatureTestsConfig.class)
public @interface IntegrationAccountFeatureTest {
    
}
