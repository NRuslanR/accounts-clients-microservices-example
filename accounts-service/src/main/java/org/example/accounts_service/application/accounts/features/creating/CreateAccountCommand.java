package org.example.accounts_service.application.accounts.features.creating;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CreateAccountCommand 
{
    @NonNull
    private String name;

    private int amount;
}
