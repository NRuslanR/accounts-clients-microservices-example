package org.example.accounts_view_service.application.events.consumers;

import org.example.accounts_events.AccountCreated;
import org.example.accounts_events.AccountCredited;
import org.example.accounts_events.AccountDebited;
import org.example.accounts_view_service.application.events.services.AccountViewEventsService;
import org.springframework.stereotype.Component;

import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AccountEventsConsumer 
{
    private final AccountViewEventsService accountViewEventsService;
 
    public DomainEventHandlers defineEventHandlers()
    {
        return
            DomainEventHandlersBuilder
                .forAggregateType("org.example.accounts_service.application.accounts.domain.Account")
                .onEvent(AccountCreated.class, this::handleAccountCreated)
                .onEvent(AccountCredited.class, this::handleAccountCredited)
                .onEvent(AccountDebited.class, this::handleAccountDebited)
                .build();
    }

    private void handleAccountCreated(DomainEventEnvelope<AccountCreated> eventEnvelope)
    {

    }

    private void handleAccountCredited(DomainEventEnvelope<AccountCredited> eventEnvelope)
    {
        
    }

    private void handleAccountDebited(DomainEventEnvelope<AccountDebited> eventEnvelope)
    {
        
    }
}
