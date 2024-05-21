package org.example.accounts_service.application.accounts.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.example.accounts_service.application.shared.domain.entities.BaseEntity;

import io.eventuate.tram.events.common.DomainEvent;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Account extends BaseEntity
{
    public static AccountWithEvents of(UUID id, String name, int amount) 
        throws NullPointerException, AccountException
    {
        var account = new Account(id, name, amount);

        var accountCreated = AccountCreated.of(UUID.randomUUID(), id, name, amount);

        return new AccountWithEvents(account, accountCreated);
    }

    private String name;

    private int amount;

    private Account(UUID id, String name, int amount)
    {
        super(id);
        
        setName(name);
        setAmount(amount);
    }

    private Account()
    {

    }
    
    public List<DomainEvent> deposit(int value) throws AccountException
    {
        if (value <= 0)
        {
            throw new AccountException("Attempt to deposit the incorrect value");
        }

        setAmount(getAmount() + value);

        return Arrays.asList(AccountCredited.of(UUID.randomUUID(), getId(), getAmount(), value)  );
    }

    public List<DomainEvent> withdraw(int value) throws AccountException
    {
        if (value <= 0)
        {
            throw new AccountException("Attempt to withdraw the incorrect value");
        }

        if (getAmount() < value)
        {
            throw new AccountException("Balance isn't enough to withdrawal");
        }

        setAmount(getAmount() - value);

        return Arrays.asList(AccountDebited.of(UUID.randomUUID(), getId(), getAmount(), value));
    }

    @Override
    public boolean equals(Object other)
    {
        return 
            !Objects.isNull(other) && 
            getClass().equals(other.getClass()) && 
            getId().equals(((Account)other).getId());
    }

    public void setName(String value) throws AccountException
    {
        if (StringUtils.isBlank(value))
        {
            throw new AccountException("Account's name cant'be blank");
        }

        this.name = value;
    }

    private void setAmount(int value)
    {
        if (value < 0)
        {
            throw new AccountException("Account's amount can't be negative"); 
        }

        this.amount = value;
    }
}
