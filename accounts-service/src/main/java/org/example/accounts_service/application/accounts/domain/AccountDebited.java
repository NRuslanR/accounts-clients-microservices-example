package org.example.accounts_service.application.accounts.domain;

import java.util.UUID;

import org.example.accounts_service.application.shared.domain.events.AppDomainEvent;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountDebited extends AppDomainEvent {

    public static AccountDebited of(UUID id, UUID aggregateId, int balance, int withdrawAmount)
    {
        return new AccountDebited(id, aggregateId, balance, withdrawAmount);
    }

    private int balance;
    private int withdrawAmount;

    private AccountDebited(UUID id, UUID aggregateId, int balance, int withdrawAmount)
    {
        super(id, aggregateId);

        setBalance(balance);
        setWithdrawAmount(withdrawAmount);
    }
}
