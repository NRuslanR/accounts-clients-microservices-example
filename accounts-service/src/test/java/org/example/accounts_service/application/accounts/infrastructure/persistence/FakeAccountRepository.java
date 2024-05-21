package org.example.accounts_service.application.accounts.infrastructure.persistence;

import org.example.accounts_service.application.accounts.domain.Account;
import org.example.accounts_service.application.shared.fakes.FakeDomainEntityRepository;
import org.springframework.boot.test.context.TestComponent;

@TestComponent
public class FakeAccountRepository extends FakeDomainEntityRepository<Account> implements AccountRepository 
{
    
}
