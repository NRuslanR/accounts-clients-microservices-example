package org.example.accounts_service.application.accounts.features.creating.saga;

import org.example.accounts_service.application.accounts.features.creating.CreateAccount;
import org.example.clients_service.application.commands.handlers.ClientNotFound;
import org.example.clients_service.application.commands.handlers.ClientReservationExpired;

import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.sagas.orchestration.SagaDefinition;
import io.eventuate.tram.sagas.simpledsl.SimpleSaga;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class CreateAccountSaga implements SimpleSaga<CreateAccountSagaData> 
{
    private final CreateAccount createAccount;

    private SagaDefinition sagaDefinition =
        step()
            .invokeLocal(this::createAccount)
            .withCompensation(this::rejectAccountCreation)
        .step()
            .invokeParticipant(this::validateClientReservationExpiration)
            .onReply(ClientNotFound.class, this::handleClientNotFound)
            .onReply(ClientReservationExpired.class, this::handleClientReservationExpired)
        .step()
            .invokeLocal(this::approveAccountCreation)
        .build();

    @Override
    public SagaDefinition<CreateAccountSagaData> getSagaDefinition() 
    {
        return sagaDefinition;
    }
    
    private void createAccount(CreateAccountSagaData sagaData)
    {

    }

    private void rejectAccountCreation(CreateAccountSagaData sagaData)
    {

    }

    private CommandWithDestination validateClientReservationExpiration(CreateAccountSagaData sagaData)
    {
        return null;
    }

    private void handleClientNotFound(CreateAccountSagaData data, ClientNotFound clientNotFound)
    {

    }

    private void handleClientReservationExpired(CreateAccountSagaData data, ClientReservationExpired clientReservationExpired)
    {

    }

    private void approveAccountCreation(CreateAccountSagaData sagaData)
    {

    }
}
