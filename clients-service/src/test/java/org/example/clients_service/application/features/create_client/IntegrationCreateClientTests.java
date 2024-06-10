package org.example.clients_service.application.features.create_client;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;

import org.example.clients_service.application.infrastructure.persistence.ClientRepository;
import org.example.clients_service.application.shared.config.ClientsIntegrationFeatureTest;
import org.springframework.beans.factory.annotation.Autowired;

@ClientsIntegrationFeatureTest
public class IntegrationCreateClientTests extends CreateClientTests
{
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    public IntegrationCreateClientTests(CreateClient createClient) 
    {
        super(createClient);
    }

    @Override
    protected void assertCreateClientResult(
        CreateClientResult result, CreateClientCommand command
    ) 
    {
        super.assertCreateClientResult(result, command);

        var client = result.getClient();

        var savedClient = clientRepository.findById(UUID.fromString(client.getId()));

        assertTrue(savedClient.isPresent());
        assertThat(client.getId(), is(equalTo(savedClient.get().getId().toString())));
    }

}
