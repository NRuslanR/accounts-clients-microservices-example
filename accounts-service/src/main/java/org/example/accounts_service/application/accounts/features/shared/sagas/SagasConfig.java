package org.example.accounts_service.application.accounts.features.shared.sagas;

import org.example.accounts_service.application.accounts.features.creating.saga.CreateAccountSagaConfig;
import org.example.accounts_service.application.accounts.features.shared.sagas.proxies.SagasProxiesConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
    SagasProxiesConfig.class,
    CreateAccountSagaConfig.class
})
public class SagasConfig 
{
}
