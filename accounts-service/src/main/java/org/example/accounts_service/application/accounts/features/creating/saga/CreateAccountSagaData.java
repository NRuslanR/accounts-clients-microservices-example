package org.example.accounts_service.application.accounts.features.creating.saga;

import org.example.accounts_service.application.accounts.domain.AccountCreationRejectionReason;
import org.example.accounts_service.application.accounts.features.creating.CreateAccountCommand;
import org.example.accounts_service.application.accounts.features.creating.CreateAccountResult;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreateAccountSagaData 
{
    public static CreateAccountSagaData of(CreateAccountCommand command)
    {
        return new CreateAccountSagaData(command);
    }
    private CreateAccountCommand command;
    private CreateAccountResult commandResult;

    private AccountCreationRejectionReason rejectionReason;

    private CreateAccountSagaData(CreateAccountCommand command)
    {
        this.command = command;
    }
}
