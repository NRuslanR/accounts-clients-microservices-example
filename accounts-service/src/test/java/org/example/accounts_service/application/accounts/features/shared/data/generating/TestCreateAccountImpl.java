package org.example.accounts_service.application.accounts.features.shared.data.generating;

import java.util.Random;
import java.util.UUID;

import org.example.accounts_service.application.accounts.features.AccountDto;
import org.example.accounts_service.application.accounts.features.creating.CreateAccount;
import org.example.accounts_service.application.accounts.features.creating.CreateAccountCommand;
import org.springframework.boot.test.context.TestComponent;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@TestComponent
public class TestCreateAccountImpl implements TestCreateAccount 
{
    private final CreateAccount createAccount;

    @Override
    public AccountDto createRandomAccount() 
    {
        return createAccount(new Random().nextInt(222));   
    }

    @Override
    public AccountDto createAccount(int amount) 
    {
        return createAccount.run(
            CreateAccountCommand.of(
                UUID.randomUUID().toString(), 
                amount
            )
        ).getAccount();    
    }
    
}
