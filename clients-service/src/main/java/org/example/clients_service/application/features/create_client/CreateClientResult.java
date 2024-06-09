package org.example.clients_service.application.features.create_client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CreateClientResult 
{
    private ClientDto client;
}
