package org.example.accounts_service.application.accounts.features.creating;

import org.example.accounts_service.application.accounts.features.shared.config.UnitAccountFeatureTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@UnitAccountFeatureTest
@ContextConfiguration(classes = {
    UnitCreateAccountTestsConfig.class
})
public class UnitCreateAccountTests extends CreateAccountTests
{
    @Autowired
    public UnitCreateAccountTests(CreateAccount createAccount) 
    {
        super(createAccount);
    }
}
