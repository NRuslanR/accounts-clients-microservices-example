package org.example.accounts_service.application.accounts.features.withdrawal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class WithdrawAccountCommand {

    private String accountId;
    private int withdrawAmount;
}
