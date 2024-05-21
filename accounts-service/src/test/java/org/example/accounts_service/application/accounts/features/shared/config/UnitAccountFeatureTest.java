package org.example.accounts_service.application.accounts.features.shared.config;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.example.accounts_service.application.shared.config.UnitFeatureTest;
import org.springframework.test.context.ContextConfiguration;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@UnitFeatureTest
@ContextConfiguration(classes = {
    UnitAccountFeatureTestsConfig.class
})
public @interface UnitAccountFeatureTest {
    
}
