package org.example.accounts_service.application.accounts.features.withdrawal;

import org.example.accounts_service.application.accounts.features.AccountNotFoundException;

public interface WithdrawAccount {

    WithdrawAccountResult run(WithdrawAccountCommand command)
        throws NullPointerException, WithdrawAccountException, AccountNotFoundException;
    
}
