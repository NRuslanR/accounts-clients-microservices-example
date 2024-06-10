package org.example.clients_service.application.features.validate_client_reservation;

import org.example.clients_service.application.features.create_client.CreateClient;
import org.example.clients_service.application.shared.config.ClientsIntegrationFeatureTest;
import org.springframework.beans.factory.annotation.Autowired;

@ClientsIntegrationFeatureTest
public class IntegrationValidateClientReservationTests extends ValidateClientReservationTests 
{
    @Autowired
    public IntegrationValidateClientReservationTests(
        CreateClient createClient,
        ValidateClientReservation validateClientReservation
    ) 
    {
        super(createClient, validateClientReservation);
    }
    
}
