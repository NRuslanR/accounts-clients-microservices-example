package org.example.accounts_service.application.accounts.features;

import org.example.accounts_service.application.accounts.domain.Account;

public interface AccountMapper 
{
    AccountDto toDto(Account account) throws NullPointerException;
} 
