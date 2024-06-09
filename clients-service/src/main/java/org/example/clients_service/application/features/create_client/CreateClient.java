package org.example.clients_service.application.features.create_client;

import jakarta.validation.Valid;

public interface CreateClient 
{
    CreateClientResult run(@Valid CreateClientCommand command);
}
