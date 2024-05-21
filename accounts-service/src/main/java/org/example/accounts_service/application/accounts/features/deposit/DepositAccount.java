package org.example.accounts_service.application.accounts.features.deposit;

import org.example.accounts_service.application.accounts.features.AccountNotFoundException;

public interface DepositAccount 
{
    DepositAccountResult run(DepositAccountCommand command)
        throws NullPointerException, DepositAccountException, AccountNotFoundException;
} 