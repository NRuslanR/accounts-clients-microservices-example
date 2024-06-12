package org.example.accounts_events;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class AccountRejected extends AppDomainEvent
{
    public static AccountRejected of(UUID id, UUID accountId, String status, String rejectionReason)
    {
        return new AccountRejected(id, accountId, status, rejectionReason);
    } 
    
    private String status;
    private String rejectionReason;

    private AccountRejected(UUID id, UUID accountId, String status, String rejectionReason)
    {
        super(id, accountId);

        setStatus(status);
        setRejectionReason(rejectionReason);
    }
}
