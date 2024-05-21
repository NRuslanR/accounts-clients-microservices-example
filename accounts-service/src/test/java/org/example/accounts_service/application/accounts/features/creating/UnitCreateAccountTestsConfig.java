package org.example.accounts_service.application.accounts.features.creating;

import org.example.accounts_service.application.accounts.features.shared.config.UnitAccountFeatureTestsConfig;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import({
    UnitAccountFeatureTestsConfig.class,
})
public class UnitCreateAccountTestsConfig 
{
    
}
