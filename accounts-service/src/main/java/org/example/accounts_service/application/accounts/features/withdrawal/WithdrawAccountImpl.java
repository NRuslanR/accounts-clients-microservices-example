package org.example.accounts_service.application.accounts.features.withdrawal;

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
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class WithdrawAccountImpl implements WithdrawAccount 
{
    private final AccountRepository accountRepository;
    private final DomainEventPublisher domainEventPublisher;
    private final AccountMapper accountMapper;

    @Override
    @Transactional
    public WithdrawAccountResult run(@NonNull WithdrawAccountCommand command)
            throws NullPointerException, 
            WithdrawAccountException, 
            AccountNotFoundException 
    {
        ensureCommandIsValid(command);

        var accountWithEvents = doWithdrawAccount(command.getAccountId(), command.getWithdrawAmount());

        var account = accountWithEvents.result;

        accountRepository.save(account);

        domainEventPublisher.publish(account.getClass(), account.getId(), accountWithEvents.events);

        return WithdrawAccountResult.of(accountMapper.toDto(account));
    }

    private void ensureCommandIsValid(WithdrawAccountCommand command) 
        throws WithdrawAccountException
    {
        
    }

    private AccountWithEvents doWithdrawAccount(
        String accountId, int withdrawAmount
    ) throws WithdrawAccountException
    {
        var account = getAccountById(accountId);

        try
        {
            return new AccountWithEvents(account, account.withdraw(withdrawAmount));
        }

        catch (AccountException exception)
        {
            throw new WithdrawAccountException(exception.getMessage());
        }
    }

    private Account getAccountById(String accountId) throws AccountNotFoundException
    {
        return accountRepository.findById(UUID.fromString(accountId)).orElseThrow(() -> 
            new WithdrawAccountException("Account not found for withdrawal")
        );
    }
}
