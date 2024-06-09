package org.example.clients_service.application.features.create_client;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonTypeName;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
@JsonTypeName
public class ClientDto 
{
    private String id;
    private String name;
    private LocalDateTime reservationExpiredAt;
}
