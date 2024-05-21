package org.example.accounts_service.application.accounts.features.shared.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.example.accounts_service.application.shared.config.IntegrationFeatureTest;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@IntegrationFeatureTest
@Import(IntegrationAccountFeatureTestsConfig.class)
public @interface IntegrationAccountFeatureTest {
    
}
