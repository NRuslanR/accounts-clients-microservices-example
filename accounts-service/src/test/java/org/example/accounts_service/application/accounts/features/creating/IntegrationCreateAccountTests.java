package org.example.accounts_service.application.accounts.features.creating;

import org.example.accounts_service.application.accounts.features.shared.config.IntegrationAccountFeatureTest;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationAccountFeatureTest
public class IntegrationCreateAccountTests extends CreateAccountTests
{
    @Autowired
    public IntegrationCreateAccountTests(CreateAccount createAccount) 
    {
        super(createAccount);
    }
}
