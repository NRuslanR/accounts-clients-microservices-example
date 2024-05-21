package org.example.accounts_service.application.accounts.features.creating;

import org.example.accounts_service.application.accounts.features.AccountDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CreateAccountResult 
{
    private AccountDto account;
}
