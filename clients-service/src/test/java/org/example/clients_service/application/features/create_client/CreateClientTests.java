package org.example.clients_service.application.features.create_client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@TestInstance(Lifecycle.PER_CLASS)
public abstract class CreateClientTests 
{
    private final CreateClient createClient;

    @Test
    public void should_Create_Client_When_CommandIsValid()
    {
        CreateClientCommand command = 
            CreateClientCommand.of(
                "#1", 
                LocalDateTime.now().plusMonths(1)
            );

        var result = createClient.run(command);

        assertNotNull(result);
        
        var client = result.getClient();

        assertNotNull(client);
    }

    protected void assertCreateClientResult(CreateClientResult result, CreateClientCommand command)
    {
        assertNotNull(result);
        
        var client = result.getClient();

        assertNotNull(client);
        assertThat(client.getId(), is(not(emptyOrNullString())));
        assertThat(client.getName(), is(equalTo(command.getName())));
        assertThat(client.getReservationExpiredAt(), is(equalTo(command.getReservationExpiredAt())));
    }
}
