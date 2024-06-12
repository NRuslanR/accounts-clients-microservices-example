package org.example.accounts_service.application.accounts.domain;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.example.accounts_events.AccountApproved;
import org.example.accounts_events.AccountCreated;
import org.example.accounts_events.AccountCredited;
import org.example.accounts_events.AccountDebited;
import org.example.accounts_events.AccountRejected;
import org.example.accounts_service.application.shared.domain.entities.BaseEntity;

import io.eventuate.tram.events.common.DomainEvent;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@Entity
public class Account extends BaseEntity
{
    public static AccountWithEvents of(UUID id, String name, int amount, String clientId) 
        throws NullPointerException, AccountException
    {
        var account = new Account(id, name, amount, clientId);
        
        var accountCreated = 
            AccountCreated.of(
                UUID.randomUUID(), 
                id, 
                name, 
                amount,
                clientId,
                account.getStatus().toString()
            );

        return new AccountWithEvents(account, accountCreated);
    }

    private String name;
    private int amount;
    private String clientId;

    @Enumerated(EnumType.STRING)
    private AccountStatus status;

    @Enumerated(EnumType.STRING)
    @Setter(AccessLevel.PRIVATE)
    private AccountCreationRejectionReason rejectionReason;
    
    private Account(UUID id, String name, int amount, String clientId)
    {
        super(id);
        
        setName(name);
        setAmount(amount);
        setClientId(clientId);
        setStatus(AccountStatus.Pending);
    }

    private Account()
    {

    }
    
    public boolean isApproved()
    {
        return getStatus().equals(AccountStatus.Approved);
    }

    public boolean isRejected()
    {
        return getStatus().equals(AccountStatus.Rejected);
    }

    public List<DomainEvent> approve()
    {
        setStatus(AccountStatus.Approved);

        return List.of(AccountApproved.of(UUID.randomUUID(), getId(), getStatus().toString()));
    }

    public List<DomainEvent> reject(AccountCreationRejectionReason rejectionReason)
    {
        setStatus(AccountStatus.Rejected);
        setRejectionReason(rejectionReason);

        return List.of(AccountRejected.of(UUID.randomUUID(), getId(), getStatus().toString(), getRejectionReason().toString()));
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

    public void setName(String value) throws AccountException
    {
        if (StringUtils.isBlank(value))
        {
            throw new AccountException("Account's name cant'be blank");
        }

        this.name = value;
    }

    public void setClientId(String value) throws AccountException
    {
        if (StringUtils.isBlank(value))
        {
            throw new AccountException("Client can't be unspecified");
        }

        this.clientId = value;
    }

    @Override
    public boolean equals(Object other)
    {
        return 
            !Objects.isNull(other) && 
            getClass().equals(other.getClass()) && 
            getId().equals(((Account)other).getId());
    }

    private void setStatus(AccountStatus value)
    { 
        switch (status)
        {
            case Pending:
            {
                if (value == AccountStatus.Pending)
                    throw new AccountException("Account is already pending");

                break;
            }

            case Approved:
            {
                if (value == AccountStatus.Approved)
                    throw new AccountException("Account is already approved");

                if (value == AccountStatus.Rejected)
                    throw new AccountException("Account can't be rejected as it is already approved");
                
                break;
            }

            case Rejected:
            {
                if (value == AccountStatus.Approved)
                    throw new AccountException("Account can't be approved as it is already rejected");

                if (value == AccountStatus.Rejected)
                    throw new AccountException("Account is already approved");
                
                break;
            }
        }

        this.status = value;
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
