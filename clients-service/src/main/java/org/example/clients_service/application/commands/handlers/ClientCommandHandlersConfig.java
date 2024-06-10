package org.example.clients_service.application.commands.handlers;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.eventuate.tram.sagas.participant.SagaCommandDispatcher;
import io.eventuate.tram.sagas.participant.SagaCommandDispatcherFactory;

@Configuration
public class ClientCommandHandlersConfig 
{
    @Bean
    public ClientCommandHandlers clientCommandHandlers()
    {
        return new ClientCommandHandlers();
    }

    @Bean
    public SagaCommandDispatcher sagaCommandDispatcher(
        SagaCommandDispatcherFactory sagaCommandDispatcherFactory
    )
    {
        return sagaCommandDispatcherFactory.make(
            "ClientsCommandDispatcher", 
            clientCommandHandlers().commandHandlers()
        );
    }
}
