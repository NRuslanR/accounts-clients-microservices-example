package org.example.accounts_service.application.accounts.features.deposit;

import java.util.UUID;

import org.example.accounts_service.application.accounts.domain.Account;
import org.example.accounts_service.application.accounts.domain.AccountException;
import org.example.accounts_service.application.accounts.domain.AccountWithEvents;
import org.example.accounts_service.application.accounts.features.AccountMapper;
import org.example.accounts_service.application.accounts.features.AccountNotFoundException;
import org.example.accounts_service.application.accounts.infrastructure.persistence.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import io.micrometer.common.lang.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DepositAccountImpl implements DepositAccount 
{
    private final AccountRepository accountRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final AccountMapper accountMapper;

    @Override
    @Transactional
    public DepositAccountResult run(@NonNull DepositAccountCommand command)
            throws NullPointerException, DepositAccountException, AccountNotFoundException
    {
        ensureCommandIsValid(command);

        var accountWithEvents = doDepositAccount(command.getAccountId(), command.getDepositAmount());
        
        var account = accountWithEvents.result;

        accountRepository.save(account);

        domainEventPublisher.publish(account.getClass(), account.getId(), accountWithEvents.events);

        return DepositAccountResult.of(accountMapper.toDto(account));
    }

    private AccountWithEvents doDepositAccount(@NonNull String accountId, int amount) 
        throws AccountNotFoundException, DepositAccountException
    {
        var account = getAccountById(accountId);
        
        try
        {
            return new AccountWithEvents(account, account.deposit(amount));
        }

        catch (AccountException exception)
        {
            throw new DepositAccountException(exception.getMessage());
        }
    }

    private void ensureCommandIsValid(DepositAccountCommand command) 
        throws DepositAccountException 
    {
        
    }

    private Account getAccountById(@NonNull String accountId) 
        throws AccountNotFoundException
    {
        return accountRepository.findById(UUID.fromString(accountId)).orElseThrow(
            () -> new AccountNotFoundException("Account not found to deposit")
        );
    }
    
}
