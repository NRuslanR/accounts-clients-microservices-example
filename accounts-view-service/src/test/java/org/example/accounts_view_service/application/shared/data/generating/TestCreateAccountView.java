package org.example.accounts_view_service.application.shared.data.generating;

import org.example.accounts_view_service.application.features.shared.AccountView;

import reactor.core.publisher.Mono;

public interface TestCreateAccountView 
{
    Mono<AccountView> createRandomAccountView();
}
