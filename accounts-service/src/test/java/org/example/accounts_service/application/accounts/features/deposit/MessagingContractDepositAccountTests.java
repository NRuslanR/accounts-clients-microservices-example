package org.example.accounts_service.application.accounts.features.deposit;

import java.util.List;
import java.util.UUID;

import org.example.accounts_events.AccountCredited;
import org.example.test_extensions.CDCProducerTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.jna.platform.win32.Advapi32Util.Account;

import io.eventuate.tram.events.publisher.DomainEventPublisher;

@CDCProducerTest
public class MessagingContractDepositAccountTests 
{
    @Autowired
    private DomainEventPublisher domainEventPublisher;

    public void accountCredited()
    {
        var aggregateId = UUID.randomUUID();
        
        domainEventPublisher.publish(
            Account.class, 
            aggregateId, 
            List.of(
                AccountCredited.of(
                    UUID.randomUUID(), 
                    aggregateId, 
                    34,
                    12
                )
            )
        );
    }
}
