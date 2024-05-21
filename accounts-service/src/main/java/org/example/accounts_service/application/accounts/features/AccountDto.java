package org.example.accounts_service.application.accounts.features;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class AccountDto 
{
    private String id;
    private String name;
    private int amount;
}
