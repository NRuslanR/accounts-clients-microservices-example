package org.example.accounts_service.application.accounts.api;

import org.example.accounts_service.application.accounts.features.creating.CreateAccountCommand;
import org.example.accounts_service.application.accounts.features.creating.CreateAccountResult;
import org.example.accounts_service.application.accounts.features.creating.saga.CreateAccountSagaService;
import org.example.accounts_service.application.accounts.features.deposit.DepositAccount;
import org.example.accounts_service.application.accounts.features.deposit.DepositAccountCommand;
import org.example.accounts_service.application.accounts.features.deposit.DepositAccountResult;
import org.example.accounts_service.application.accounts.features.withdrawal.WithdrawAccount;
import org.example.accounts_service.application.accounts.features.withdrawal.WithdrawAccountCommand;
import org.example.accounts_service.application.accounts.features.withdrawal.WithdrawAccountResult;
import org.example.accounts_service.application.shared.infrastructure.security.annotations.AccountsUserAllowed;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AccountsUserAllowed
public class AbstractAccountsApi implements AccountsApi
{
    private final CreateAccountSagaService createAccountSagaService;
    private final DepositAccount depositAccount;
    private final WithdrawAccount withdrawAccount;

    @Override
    public CreateAccountResult createAccount(CreateAccountCommand command) 
    {    
        return createAccountSagaService.createAccount(command);
    }

    @Override
    public DepositAccountResult depositAccount(String accountId, DepositAccountCommand command) 
    {
        return depositAccount.run(command);
    }

    @Override
    public WithdrawAccountResult withdrawAccount(String accountId, WithdrawAccountCommand command) 
    {
        return withdrawAccount.run(command);
    }
}
