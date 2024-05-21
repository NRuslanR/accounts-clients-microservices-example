package org.example.accounts_service.application.accounts.api;

import org.example.accounts_service.application.accounts.features.creating.CreateAccountCommand;
import org.example.accounts_service.application.accounts.features.creating.CreateAccountResult;
import org.example.accounts_service.application.accounts.features.deposit.DepositAccountCommand;
import org.example.accounts_service.application.accounts.features.deposit.DepositAccountResult;
import org.example.accounts_service.application.accounts.features.withdrawal.WithdrawAccountCommand;
import org.example.accounts_service.application.accounts.features.withdrawal.WithdrawAccountResult;

public interface AccountsApi 
{
    public CreateAccountResult createAccount(CreateAccountCommand command);
    public DepositAccountResult depositAccount(String accountId, DepositAccountCommand command);
    public WithdrawAccountResult withdrawAccount(String accountId, WithdrawAccountCommand command);
}
