package org.example.accounts_service.application.accounts.features.creating.saga;

import org.example.accounts_service.application.accounts.domain.AccountCreationRejectionReason;
import org.example.accounts_service.application.accounts.features.creating.ApproveCreationAccountCommand;
import org.example.accounts_service.application.accounts.features.creating.CreateAccount;
import org.example.accounts_service.application.accounts.features.creating.RejectCreationAccountCommand;
import org.example.accounts_service.application.accounts.features.shared.sagas.proxies.ClientsServiceProxy;
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
    private final ClientsServiceProxy clientsServiceProxy;

    private SagaDefinition<CreateAccountSagaData> sagaDefinition =
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
        var result = createAccount.run(sagaData.getCommand());

        sagaData.setCommandResult(result);
    }

    private void rejectAccountCreation(CreateAccountSagaData sagaData)
    {
        var command =
            RejectCreationAccountCommand.of(
                sagaData.getCommandResult().getAccount().getId(),
                sagaData.getRejectionReason().toString()
            );

        createAccount.reject(command);
    }

    private CommandWithDestination validateClientReservationExpiration(CreateAccountSagaData sagaData)
    {
        var accountDetails = sagaData.getCommand();

        return clientsServiceProxy.validateClientReservationExpiration(accountDetails.getClientId());
    }

    private void handleClientNotFound(CreateAccountSagaData sagaData, ClientNotFound clientNotFound)
    {
        sagaData.setRejectionReason(AccountCreationRejectionReason.ClientNotFound);
    }

    private void handleClientReservationExpired(CreateAccountSagaData sagaData, ClientReservationExpired clientReservationExpired)
    {
        sagaData.setRejectionReason(AccountCreationRejectionReason.ClientReservationExpired);
    }

    private void approveAccountCreation(CreateAccountSagaData sagaData)
    {
        createAccount.approve(ApproveCreationAccountCommand.of(sagaData.getCommandResult().getAccount().getId()));
    }
}
