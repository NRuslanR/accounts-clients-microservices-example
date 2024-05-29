package org.example.accounts_events;

import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AccountCredited extends AppDomainEvent 
{
    public static AccountCredited of(UUID id, UUID aggregateId, int balance, int depositAmount)
    {
        return new AccountCredited(id, aggregateId, balance, depositAmount);
    }

    private int balance;
    private int depositAmount;

    private AccountCredited(UUID id, UUID aggregateId, int balance, int depositAmount)
    {
        super(id, aggregateId);

        setBalance(balance);
        setDepositAmount(depositAmount);
    }
}
