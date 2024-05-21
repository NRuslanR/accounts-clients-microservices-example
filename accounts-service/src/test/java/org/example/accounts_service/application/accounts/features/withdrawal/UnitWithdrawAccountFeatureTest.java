package org.example.accounts_service.application.accounts.features.withdrawal;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.example.accounts_service.application.accounts.features.shared.config.UnitAccountFeatureTest;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@UnitAccountFeatureTest
@Import({
    WithdrawAccountImpl.class
})
public @interface UnitWithdrawAccountFeatureTest {
    
}
