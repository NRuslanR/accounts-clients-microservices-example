package org.example.accounts_view_service.application.events.services;

import org.example.accounts_view_service.application.shared.config.IntegrationApplicationTest;
import org.example.accounts_view_service.application.shared.data.generating.TestCreateAccountView;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationApplicationTest
public class IntegrationAccountViewEventsServiceTests extends AccountViewEventsServiceTests
{
    @Autowired
    public IntegrationAccountViewEventsServiceTests(
        AccountViewEventsService accountViewEventsService,
            TestCreateAccountView testCreateAccountView
    ) 
    {
        super(accountViewEventsService, testCreateAccountView);
    }
    
}
