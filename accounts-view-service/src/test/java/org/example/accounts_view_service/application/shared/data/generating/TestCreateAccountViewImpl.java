package org.example.accounts_view_service.application.shared.data.generating;

import java.util.Random;
import java.util.UUID;

import org.example.accounts_view_service.application.features.shared.AccountView;
import org.example.accounts_view_service.application.features.shared.AccountViewRepository;
import org.springframework.boot.test.context.TestComponent;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@TestComponent
@RequiredArgsConstructor
public class TestCreateAccountViewImpl implements TestCreateAccountView
{
    private final AccountViewRepository accountViewRepository;

    @Override
    public Mono<AccountView> createRandomAccountView() 
    {
        var randomAccountView = 
            AccountView.of(
                UUID.randomUUID().toString(), 
                UUID.randomUUID().toString(), 
                new Random().nextInt(200)
            );

        return accountViewRepository.save(randomAccountView);
    }
}
