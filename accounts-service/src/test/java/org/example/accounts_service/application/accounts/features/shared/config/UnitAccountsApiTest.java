package org.example.accounts_service.application.accounts.features.shared.config;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.example.accounts_service.application.shared.config.UnitApiTest;
import org.springframework.context.annotation.Import;

@UnitApiTest
@Retention(RetentionPolicy.RUNTIME)
@Import({
    UnitAccountsApiTestsConfig.class
})
public @interface UnitAccountsApiTest {
    
}
