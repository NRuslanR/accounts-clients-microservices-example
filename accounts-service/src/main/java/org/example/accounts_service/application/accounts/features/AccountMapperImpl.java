package org.example.accounts_service.application.accounts.features;

import org.example.accounts_service.application.accounts.domain.Account;
import org.springframework.stereotype.Component;

import lombok.NonNull;

@Component
public class AccountMapperImpl implements AccountMapper
{
    @Override
    public AccountDto toDto(@NonNull Account account) throws NullPointerException 
    {
        return AccountDto.of(account.getId().toString(), account.getName(), account.getAmount());
    }
}
