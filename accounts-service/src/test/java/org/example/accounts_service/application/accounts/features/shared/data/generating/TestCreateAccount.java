package org.example.accounts_service.application.accounts.features.shared.data.generating;

import org.example.accounts_service.application.accounts.features.AccountDto;

public interface TestCreateAccount 
{
    AccountDto createRandomAccount();
    AccountDto createAccount(int amount);
}
