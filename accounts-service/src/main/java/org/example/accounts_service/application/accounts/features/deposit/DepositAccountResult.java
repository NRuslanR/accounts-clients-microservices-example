package org.example.accounts_service.application.accounts.features.deposit;

import org.example.accounts_service.application.accounts.features.AccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class DepositAccountResult 
{
    private AccountDto account;
}
