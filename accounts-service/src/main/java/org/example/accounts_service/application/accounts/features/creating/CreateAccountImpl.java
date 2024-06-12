package org.example.accounts_service.application.accounts.features.creating;

import java.util.UUID;

import org.example.accounts_service.application.accounts.domain.Account;
import org.example.accounts_service.application.accounts.domain.AccountCreationRejectionReason;
import org.example.accounts_service.application.accounts.domain.AccountException;
import org.example.accounts_service.application.accounts.domain.AccountWithEvents;
import org.example.accounts_service.application.accounts.features.AccountMapper;
import org.example.accounts_service.application.accounts.infrastructure.persistence.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.eventuate.tram.events.publisher.DomainEventPublisher;
import lombok.NonNull;

@Service
@Transactional
public class CreateAccountImpl implements CreateAccount 
{
    protected final AccountRepository accountRepository;
    protected final DomainEventPublisher domainEventPublisher;
    private final AccountMapper accountMapper;

    public CreateAccountImpl(
        AccountRepository accountRepository, 
        DomainEventPublisher domainEventPublisher,
        AccountMapper accountMapper
    )
    {
        this.accountRepository = accountRepository;
        this.domainEventPublisher = domainEventPublisher;
        this.accountMapper = accountMapper;
    }
    
    @Override
    public CreateAccountResult run(@NonNull CreateAccountCommand command) 
    throws NullPointerException, CreateAccountException 
    {
        var accountWithEvents = createAccountFromCommand(command);

        var account = accountRepository.save(accountWithEvents.result);

        domainEventPublisher.publish(
            account.getClass(), 
            account.getId(), 
            accountWithEvents.events
        );

        return CreateAccountResult.of(accountMapper.toDto(account));
    }

    @Override
    public void approve(ApproveCreationAccountCommand command) 
    {
        var account = getAccountById(command.getAccountId());

        var events = account.approve();

        accountRepository.save(account);

        domainEventPublisher.publish(
            account.getClass(), 
            account.getId(), 
            events
        );
    }

    @Override
    public void reject(RejectCreationAccountCommand command) 
    {
        var account = getAccountById(command.getAccountId());

        var events = account.reject(AccountCreationRejectionReason.valueOf(command.getRejectionReason()));
        
        accountRepository.save(account);

        domainEventPublisher.publish(
            account.getClass(), 
            account.getId(), 
            events
        );
    }

    private Account getAccountById(String accountId) 
    {
        return accountRepository.getAccountById(UUID.fromString(accountId));
    }

    private AccountWithEvents createAccountFromCommand(@NonNull CreateAccountCommand command) throws CreateAccountException
    {
        try
        {
            return Account.of(
                UUID.randomUUID(), 
                command.getName(), 
                command.getAmount(),
                command.getClientId()
            );
        }
        
        catch(AccountException exception)
        {
            throw new CreateAccountException(exception.getMessage());
        }
    }
}
