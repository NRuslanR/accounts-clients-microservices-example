package org.example.accounts_service.application.accounts.features.creating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ApproveCreationAccountCommand 
{
    private String accountId;
}
