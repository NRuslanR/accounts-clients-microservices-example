package org.example.clients_service.application.commands.handlers;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecorator;
import io.eventuate.tram.spring.optimisticlocking.OptimisticLockingDecoratorConfiguration;

@Configuration
@Import({
    OptimisticLockingDecorator.class,
    ClientCommandHandlersConfig.class
})
public class MessagingConfig 
{
    
}
