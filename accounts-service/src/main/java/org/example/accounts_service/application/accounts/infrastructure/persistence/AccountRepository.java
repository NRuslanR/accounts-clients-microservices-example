package org.example.accounts_service.application.accounts.infrastructure.persistence;

import java.util.UUID;

import org.example.accounts_service.application.accounts.domain.Account;
import org.example.accounts_service.application.accounts.features.AccountNotFoundException;
import org.example.accounts_service.application.shared.infrastructure.persistence.DomainEntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends DomainEntityRepository<Account>
{
    default Account getAccountById(UUID id)
    {
        return findById(id).orElseThrow(AccountNotFoundException::new);
    }
}