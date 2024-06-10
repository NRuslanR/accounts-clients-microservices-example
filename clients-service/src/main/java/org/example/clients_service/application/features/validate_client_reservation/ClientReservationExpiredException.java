package org.example.clients_service.application.features.validate_client_reservation;

public class ClientReservationExpiredException extends RuntimeException 
{
    public ClientReservationExpiredException() {
        super("Client's reservation has expired");
    }
 
    public ClientReservationExpiredException(String message) {
       super(message);
    }
}
