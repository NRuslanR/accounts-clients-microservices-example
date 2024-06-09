package org.example.clients_service.application.features.create_client;

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
public class CreateClientImpl implements CreateClient 
{
    private final ClientRepository clientRepository;

    @Override
    public CreateClientResult run(@Valid CreateClientCommand command) 
    {
        var client = Client.of(UUID.randomUUID(), command.getName(), command.getReservationExpiredAt());

        client = clientRepository.save(client);

        return CreateClientResult.of(
            ClientDto.of(
                client.getId().toString(), 
                client.getName(), 
                client.getReservationExpiredAt())
        );
    }
    
}
