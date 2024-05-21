package org.example.accounts_service.application.accounts.features.deposit;

import org.example.accounts_service.application.accounts.features.shared.config.IntegrationAccountFeatureTest;
import org.example.accounts_service.application.accounts.features.shared.data.generating.TestCreateAccount;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationAccountFeatureTest
public class IntegrationDepositAccountTests extends DepositAccountTests 
{
    @Autowired
    public IntegrationDepositAccountTests(
        TestCreateAccount testCreateAccount, 
        DepositAccount depositAccount) 
    {
        super(testCreateAccount, depositAccount);
    }
}
