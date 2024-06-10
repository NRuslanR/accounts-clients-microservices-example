package org.example.clients_service.application.features.validate_client_reservation;

public class ClientNotFoundException extends RuntimeException 
{
    public ClientNotFoundException() {
        super("Client not found");
    }
 
    public ClientNotFoundException(String message) {
       super(message);
    }
}
