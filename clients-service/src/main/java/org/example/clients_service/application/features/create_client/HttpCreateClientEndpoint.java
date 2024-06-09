package org.example.clients_service.application.features.create_client;

import org.example.clients_service.application.infrastructure.security.annotations.ClientsAllowed;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/clients")
@ClientsAllowed
public class HttpCreateClientEndpoint 
{
    private final CreateClient createClient;

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CreateClientResult run(@RequestBody @Valid CreateClientCommand command) 
    {
        return createClient.run(command);
    }
    
}
