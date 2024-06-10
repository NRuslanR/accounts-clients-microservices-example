package org.example.clients_service.application.features.validate_client_reservation;

import java.time.LocalDateTime;
import java.util.UUID;

import org.example.clients_service.application.domain.Client;
import org.example.clients_service.application.infrastructure.persistence.ClientRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
@Transactional
public class ValidateClientReservationImpl implements ValidateClientReservation 
{
    private final ClientRepository clientRepository;

    @Override
    public void run(@Valid ValidateClientReservationCommand command) 
        throws ClientNotFoundException, ClientReservationExpiredException
    {
        var client = getClientById(command.getClientId());

        ensureClientReservationDoesNotExpired(client);
    }

    private Client getClientById(String clientId) 
    {
        return clientRepository.findById(UUID.fromString(clientId)).orElseThrow(ClientNotFoundException::new);
    }

    private void ensureClientReservationDoesNotExpired(Client client) 
    {
        if (client.getReservationExpiredAt().isBefore(LocalDateTime.now()))
        {
            throw new ClientReservationExpiredException();
        }
    }
    
}
