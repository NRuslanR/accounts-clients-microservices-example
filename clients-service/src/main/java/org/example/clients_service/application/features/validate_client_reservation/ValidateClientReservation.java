package org.example.clients_service.application.features.validate_client_reservation;

import jakarta.validation.Valid;

public interface ValidateClientReservation 
{
    void run(@Valid ValidateClientReservationCommand command) 
        throws ClientNotFoundException, ClientReservationExpiredException;
}
