package org.example.accounts_service.application.accounts.features.withdrawal;

import java.util.List;
import java.util.UUID;

import org.example.accounts_service.application.accounts.domain.AccountDebited;
import org.example.accounts_service.application.shared.config.ConsumerDrivenContractTest;
import org.springframework.beans.factory.annotation.Autowired;

import com.sun.jna.platform.win32.Advapi32Util.Account;

import io.eventuate.tram.events.publisher.DomainEventPublisher;

@ConsumerDrivenContractTest
public class MessagingContractWithdrawAccountTests 
{
    @Autowired
    private DomainEventPublisher domainEventPublisher;

    public void accountDebited()
    {
        var accountId = UUID.randomUUID();

        domainEventPublisher.publish(
            Account.class, 
            accountId, 
            List.of(
                AccountDebited.of(
                    UUID.randomUUID(), 
                    accountId, 
                    0, 
                    42
                )
            )
        );
    }    
}
