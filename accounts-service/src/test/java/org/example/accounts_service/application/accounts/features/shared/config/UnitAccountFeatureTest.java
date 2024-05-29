package org.example.accounts_service.application.accounts.features.shared.config;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.example.accounts_service.application.shared.config.DisableIntegrationAutoConfiguration;
import org.example.test_extensions.UnitFeatureTest;
import org.springframework.test.context.ContextConfiguration;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@UnitFeatureTest
@DisableIntegrationAutoConfiguration
@ContextConfiguration(classes = {
    UnitAccountFeatureTestsConfig.class
})
public @interface UnitAccountFeatureTest {
    
}
