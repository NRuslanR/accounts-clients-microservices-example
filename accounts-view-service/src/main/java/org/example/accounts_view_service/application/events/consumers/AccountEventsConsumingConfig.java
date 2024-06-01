package org.example.accounts_view_service.application.events.consumers;

import org.example.accounts_view_service.application.events.services.AccountViewEventsService;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import io.eventuate.tram.events.subscriber.DomainEventDispatcher;
import io.eventuate.tram.events.subscriber.DomainEventDispatcherFactory;
import io.eventuate.tram.spring.consumer.common.TramConsumerCommonConfiguration;
import io.eventuate.tram.spring.consumer.common.TramNoopDuplicateMessageDetectorConfiguration;

@ConditionalOnBean(TramConsumerCommonConfiguration.class)
@Configuration
@Import({
    TramNoopDuplicateMessageDetectorConfiguration.class
})
public class AccountEventsConsumingConfig 
{
    @Bean
    public AccountEventsConsumer accountEventsConsumer(AccountViewEventsService accountViewEventsService)
    {
        return new AccountEventsConsumer(accountViewEventsService);
    }

    @Bean
    public DomainEventDispatcher domainEventDispatcher(
        DomainEventDispatcherFactory dispatcherFactory,
        AccountEventsConsumer accountEventsConsumer
    )
    {
        return dispatcherFactory.make(
            "AccountEventsDispatcher", 
            accountEventsConsumer.getEventHandlers()
        );
    }
}
