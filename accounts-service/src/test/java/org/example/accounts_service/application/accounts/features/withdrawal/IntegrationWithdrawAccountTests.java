package org.example.accounts_service.application.accounts.features.withdrawal;

import org.example.accounts_service.application.accounts.features.shared.config.IntegrationAccountFeatureTest;
import org.example.accounts_service.application.accounts.features.shared.data.generating.TestCreateAccount;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationAccountFeatureTest
public class IntegrationWithdrawAccountTests extends WithdrawAccountTests 
{
    @Autowired
    public IntegrationWithdrawAccountTests(
        TestCreateAccount testCreateAccount, 
        WithdrawAccount withdrawAccount
    ) 
    {
        super(testCreateAccount, withdrawAccount);
    }
}
