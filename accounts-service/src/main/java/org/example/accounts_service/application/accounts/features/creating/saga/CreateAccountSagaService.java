package org.example.accounts_service.application.accounts.features.creating.saga;

import org.example.accounts_service.application.accounts.features.creating.CreateAccountCommand;
import org.example.accounts_service.application.accounts.features.creating.CreateAccountResult;

import io.eventuate.tram.sagas.orchestration.SagaInstanceFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAccountSagaService 
{
    private final SagaInstanceFactory sagaInstanceFactory;
    private final CreateAccountSaga createAccountSaga;

    public CreateAccountResult createAccount(CreateAccountCommand command)
    {
        var sagaData = CreateAccountSagaData.of(command);

        sagaInstanceFactory.create(createAccountSaga, sagaData);

        return sagaData.getCommandResult();
    }
}
