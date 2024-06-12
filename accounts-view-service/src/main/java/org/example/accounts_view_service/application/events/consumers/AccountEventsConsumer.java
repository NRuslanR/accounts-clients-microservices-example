package org.example.accounts_view_service.application.events.consumers;

import org.example.accounts_events.AccountApproved;
import org.example.accounts_events.AccountCreated;
import org.example.accounts_events.AccountCredited;
import org.example.accounts_events.AccountDebited;
import org.example.accounts_events.AccountRejected;
import org.example.accounts_view_service.application.events.services.AccountViewEventsService;
import org.springframework.beans.factory.annotation.Value;

import io.eventuate.tram.events.subscriber.DomainEventEnvelope;
import io.eventuate.tram.events.subscriber.DomainEventHandlers;
import io.eventuate.tram.events.subscriber.DomainEventHandlersBuilder;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class AccountEventsConsumer 
{
    @Value("${application.events.consuming.account.destination}")
    private String accountEventsDestination;

    private final AccountViewEventsService accountViewEventsService;
 
    public DomainEventHandlers getEventHandlers()
    {
        return
            DomainEventHandlersBuilder
                .forAggregateType(accountEventsDestination)
                .onEvent(AccountCreated.class, this::handleAccountCreated)
                .onEvent(AccountApproved.class, this::handleAccountApproved)
                .onEvent(AccountRejected.class, this::handleAccountRejected)
                .onEvent(AccountCredited.class, this::handleAccountCredited)
                .onEvent(AccountDebited.class, this::handleAccountDebited)
                .build();
    }

    private void handleAccountCreated(DomainEventEnvelope<AccountCreated> eventEnvelope)
    {
        var accountCreated = eventEnvelope.getEvent();

        accountViewEventsService.applyAccountCreated(accountCreated);
    }

    private void handleAccountApproved(DomainEventEnvelope<AccountApproved> eventEnvelope)
    {
        var accountCreated = eventEnvelope.getEvent();

        accountViewEventsService.applyAccountApproved(accountCreated);
    }

    private void handleAccountRejected(DomainEventEnvelope<AccountRejected> eventEnvelope)
    {
        var accountCreated = eventEnvelope.getEvent();

        accountViewEventsService.applyAccountRejected(accountCreated);
    }

    private void handleAccountCredited(DomainEventEnvelope<AccountCredited> eventEnvelope)
    {
        var accountCredited = eventEnvelope.getEvent();

        accountViewEventsService.applyAccountCredited(accountCredited);
    }

    private void handleAccountDebited(DomainEventEnvelope<AccountDebited> eventEnvelope)
    {
        var accountDebited = eventEnvelope.getEvent();

        accountViewEventsService.applyAccountDebited(accountDebited);
    }
}
