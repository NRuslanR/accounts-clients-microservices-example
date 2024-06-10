package org.example.clients_service.application.features.validate_client_reservation;

import io.eventuate.tram.commands.common.Command;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor(staticName = "of")
public class ValidateClientReservationCommand implements Command
{
    @NotBlank
    private String clientId;
}
