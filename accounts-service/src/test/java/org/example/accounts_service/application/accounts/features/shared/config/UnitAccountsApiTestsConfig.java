package org.example.accounts_service.application.accounts.features.shared.config;

import org.example.accounts_service.application.accounts.features.deposit.DepositAccountImpl;
import org.example.accounts_service.application.accounts.features.withdrawal.WithdrawAccountImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import({
    UnitAccountFeatureTestsConfig.class,
    DepositAccountImpl.class,
    WithdrawAccountImpl.class
})
public class UnitAccountsApiTestsConfig 
{
    
}
