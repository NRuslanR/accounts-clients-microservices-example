package org.example.accounts_service.application.accounts.features.withdrawal;

import org.example.accounts_service.application.accounts.features.shared.data.generating.TestCreateAccount;
import org.springframework.beans.factory.annotation.Autowired;

@UnitWithdrawAccountFeatureTest
public class UnitWithdrawAccountTests extends WithdrawAccountTests {

    @Autowired
    public UnitWithdrawAccountTests(
        TestCreateAccount testCreateAccount, 
        WithdrawAccount withdrawAccount
    ) 
    {
        super(testCreateAccount, withdrawAccount);
    }
    
}
