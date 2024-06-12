package org.example.accounts_service.application.accounts.features.creating.saga;

import org.example.accounts_service.application.accounts.features.creating.CreateAccount;
import org.example.accounts_service.application.accounts.features.shared.sagas.proxies.ClientsServiceProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;

@Configuration
public class CreateAccountSagaConfig 
{
    @Bean
    public CreateAccountSaga createAccountSaga(CreateAccount createAccount, ClientsServiceProxy clientsServiceProxy)
    {
        return new CreateAccountSaga(createAccount, clientsServiceProxy);
    }
    
    @Bean
    public CreateAccountSagaService createAccountSagaService(CreateAccountSaga createAccountSaga, SagaInstanceFactory sagaInstanceFactory)
    {
        return new CreateAccountSagaService(sagaInstanceFactory, createAccountSaga);
    }
}
