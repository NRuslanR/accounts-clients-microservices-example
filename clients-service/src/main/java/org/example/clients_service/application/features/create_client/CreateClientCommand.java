package org.example.clients_service.application.features.create_client;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class CreateClientCommand 
{
    @NotBlank
    private String name;

    @NotNull
    private LocalDateTime reservationExpiredAt;
}
