package org.example.accounts_service.application.accounts.domain;

import java.util.UUID;

import org.example.accounts_service.application.shared.domain.events.AppDomainEvent;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class AccountCreated extends AppDomainEvent
{
    public static AccountCreated of(UUID id, UUID accountId, String name, int amount)
    {
        return new AccountCreated(id, accountId, name, amount);
    }

    @NonNull
    private String name;
    
    private int amount;

    private AccountCreated(UUID id, UUID accountId, String name, int amount)
    {
        super(id, accountId);

        setName(name);
        setAmount(amount);
    }
}
