package org.example.accounts_service.application.accounts.features.deposit;

import org.example.accounts_service.application.accounts.features.shared.data.generating.TestCreateAccount;
import org.springframework.beans.factory.annotation.Autowired;

@UnitDepositAccountFeatureTest
public class UnitDepositAccountTests extends DepositAccountTests 
{
    @Autowired
    public UnitDepositAccountTests(
        TestCreateAccount testCreateAccount, 
        DepositAccount depositAccount) 
    {
        super(testCreateAccount, depositAccount);
    }
    
}
