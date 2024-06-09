package org.example.clients_service.application.features.create_client;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.example.clients_service.application.domain.Client;
import org.example.clients_service.application.infrastructure.persistence.ClientRepository;
import org.example.clients_service.application.shared.config.ClientsUnitFeatureTest;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;

@ClientsUnitFeatureTest
@ContextConfiguration(classes = CreateClientImpl.class)
public class UnitCreateClientTests extends CreateClientTests 
{
    @MockBean
    private ClientRepository clientRepository;

    @Autowired
    public UnitCreateClientTests(CreateClient createClient) 
    {
        super(createClient);
    }

    @BeforeAll
    public void setupAll()
    {
        when(clientRepository.save(any(Client.class))).then(i -> i.getArgument(0));
    }

    @Override
    protected void assertCreateClientResult(CreateClientResult result, CreateClientCommand command) {
        
        super.assertCreateClientResult(result, command);

        verify(clientRepository).save(any(Client.class));
    }
}
