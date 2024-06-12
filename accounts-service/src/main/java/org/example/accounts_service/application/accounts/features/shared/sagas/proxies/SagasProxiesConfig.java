package org.example.accounts_service.application.accounts.features.shared.sagas.proxies;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SagasProxiesConfig 
{
    @Bean
    public ClientsServiceProxy clientsServiceProxy()
    {
        return new ClientsServiceProxy();
    }
}
