package org.example.accounts_service.application.accounts.features.shared.sagas.proxies;

import org.example.clients_service.application.features.validate_client_reservation.ValidateClientReservationCommand;

import io.eventuate.tram.commands.consumer.CommandWithDestination;
import io.eventuate.tram.commands.consumer.CommandWithDestinationBuilder;

public class ClientsServiceProxy 
{
    public CommandWithDestination validateClientReservationExpiration(
        String clientId
    ) 
    {
        return 
            CommandWithDestinationBuilder
                .send(ValidateClientReservationCommand.of(clientId))
                .to("clientsService")
                .build();
    }
}
