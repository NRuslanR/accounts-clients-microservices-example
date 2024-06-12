package org.example.accounts_events;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class AccountApproved extends AppDomainEvent
{
    public static AccountApproved of(UUID id, UUID accountId, String status)
    {
        return new AccountApproved(id, accountId, status);
    } 
    
    private String status;
    
    private AccountApproved(UUID id, UUID accountId, String status)
    {
        super(id, accountId);

        setStatus(status);
    }
}
