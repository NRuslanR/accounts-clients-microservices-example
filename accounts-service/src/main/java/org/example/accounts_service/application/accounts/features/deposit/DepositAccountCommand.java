package org.example.accounts_service.application.accounts.features.deposit;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class DepositAccountCommand 
{
    @NonNull
    private String accountId;
    
    private int depositAmount;
}
