package org.example.accounts_service.application.accounts.features.shared.config;

import org.example.accounts_service.application.accounts.features.shared.data.generating.TestCreateAccountImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import({
    TestCreateAccountImpl.class
})
public class IntegrationAccountFeatureTestsConfig {
    
}
