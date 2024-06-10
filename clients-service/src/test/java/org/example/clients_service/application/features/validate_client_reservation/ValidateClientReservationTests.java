package org.example.clients_service.application.features.validate_client_reservation;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.UUID;

import org.example.clients_service.application.features.create_client.ClientDto;
import org.example.clients_service.application.features.create_client.CreateClient;
import org.example.clients_service.application.features.create_client.CreateClientCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@TestInstance(Lifecycle.PER_CLASS)
public abstract class ValidateClientReservationTests 
{
    private final CreateClient createClient;
    private final ValidateClientReservation validateClientReservation;

    @Test
    public void should_SuccessfulValidate_ClientReservation_When_ClientExists_And_ReservationDoesNotExpired()
    {
        var client = createRandomClient();

        var command = ValidateClientReservationCommand.of(client.getId());

        validateClientReservation.run(command);
    }

    @Test
    public void should_Raise_Error_When_ClientDoesNotExists()
    {
        var command = ValidateClientReservationCommand.of(UUID.randomUUID().toString());

        assertThrows(ClientNotFoundException.class, () -> {

            validateClientReservation.run(command);

        });
    }

    @Test
    public void should_Raise_Error_When_ReservationExpired()
    {
        var client = createRandomClient(LocalDateTime.now().minusDays(1));

        var command = ValidateClientReservationCommand.of(client.getId());

        assertThrows(ClientReservationExpiredException.class, () -> {

            validateClientReservation.run(command);

        });
    }

    private ClientDto createRandomClient() 
    {
        return createRandomClient(LocalDateTime.now().plusDays(1));
    }    

    private ClientDto createRandomClient(LocalDateTime expiredAt) 
    {
        var result = createClient.run(CreateClientCommand.of("#1", expiredAt));

        return result.getClient();
    }  
}
