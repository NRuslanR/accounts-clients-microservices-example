package org.example.accounts_service.application.accounts.features.creating;

import java.util.UUID;

import org.example.accounts_service.application.accounts.domain.Account;
import org.example.test_extensions.ConsumerDrivenContractTest;
import org.springframework.beans.factory.annotation.Autowired;

import io.eventuate.tram.events.publisher.DomainEventPublisher;

@ConsumerDrivenContractTest
public class MessagingContractCreateAccountTests 
{
    @Autowired
    private DomainEventPublisher domainEventPublisher;

    public void accountCreated()
    {
        var accountWithEvents = Account.of(UUID.randomUUID(), "#1", 12);

        var account = accountWithEvents.result;

        domainEventPublisher.publish(
            account.getClass(), 
            account.getId(), 
            accountWithEvents.events
        );
    }
}
