package org.example.accounts_service.application.accounts.features.shared.config;

import org.example.accounts_service.application.accounts.features.AccountMapperImpl;
import org.example.accounts_service.application.accounts.features.creating.UnitCreateAccountImpl;
import org.example.accounts_service.application.accounts.features.shared.data.generating.TestCreateAccountImpl;
import org.example.accounts_service.application.accounts.infrastructure.persistence.FakeAccountRepository;
import org.example.accounts_service.application.shared.fakes.FakeDomainEventPublisher;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import({
    UnitCreateAccountImpl.class,
    FakeAccountRepository.class,
    FakeDomainEventPublisher.class,
    AccountMapperImpl.class,
    TestCreateAccountImpl.class
})
public class UnitAccountFeatureTestsConfig {
    
}
