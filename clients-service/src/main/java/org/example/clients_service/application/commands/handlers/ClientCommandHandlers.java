package org.example.clients_service.application.commands.handlers;

import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withFailure;
import static io.eventuate.tram.commands.consumer.CommandHandlerReplyBuilder.withSuccess;

import org.example.clients_service.application.features.validate_client_reservation.ClientNotFoundException;
import org.example.clients_service.application.features.validate_client_reservation.ClientReservationExpiredException;
import org.example.clients_service.application.features.validate_client_reservation.ValidateClientReservation;
import org.example.clients_service.application.features.validate_client_reservation.ValidateClientReservationCommand;
import org.springframework.beans.factory.annotation.Autowired;

import io.eventuate.tram.commands.consumer.CommandHandlers;
import io.eventuate.tram.commands.consumer.CommandMessage;
import io.eventuate.tram.messaging.common.Message;
import io.eventuate.tram.sagas.participant.SagaCommandHandlersBuilder;

public class ClientCommandHandlers 
{
    @Autowired
    private ValidateClientReservation validateClientReservation;
    
    public CommandHandlers commandHandlers()
    {
        return 
            SagaCommandHandlersBuilder
                .fromChannel("clientsService")
                .onMessage(
                    ValidateClientReservationCommand.class, 
                    this::handleValidateClientReservationCommand
                )
                .build();
    }

    private Message handleValidateClientReservationCommand(CommandMessage<ValidateClientReservationCommand> commandMessage)
    {
        var command = commandMessage.getCommand();

        try
        {
            validateClientReservation.run(command);

            return withSuccess();
        }

        catch (ClientNotFoundException exception)
        {
            return withFailure(new ClientNotFound());
        }

        catch (ClientReservationExpiredException exception)
        {
            return withFailure(new ClientReservationExpired());
        }
    }
}
