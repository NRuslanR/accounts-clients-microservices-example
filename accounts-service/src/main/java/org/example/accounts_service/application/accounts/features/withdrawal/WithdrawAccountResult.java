package org.example.accounts_service.application.accounts.features.withdrawal;

import org.example.accounts_service.application.accounts.features.AccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class WithdrawAccountResult {

    private AccountDto account;
    
}
