package org.example.accounts_service.application.accounts.features.creating;

import org.example.accounts_service.application.accounts.features.AccountMapper;
import org.example.accounts_service.application.accounts.infrastructure.persistence.FakeAccountRepository;
import org.example.accounts_service.application.shared.fakes.FakeDomainEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Primary;

@Primary
@TestComponent
public class UnitCreateAccountImpl extends CreateAccountImpl 
{
    @Autowired
    public UnitCreateAccountImpl(
        FakeAccountRepository accountRepository, 
        FakeDomainEventPublisher domainEventPublisher,
        AccountMapper accountMapper
    ) 
    {
        super(accountRepository, domainEventPublisher, accountMapper);
    }

    public FakeAccountRepository getFakeAccountRepository()
    {
        return (FakeAccountRepository)accountRepository;
    }

    public FakeDomainEventPublisher getFakeDomainEventPublisher()
    {
        return (FakeDomainEventPublisher)domainEventPublisher;
    }
}
