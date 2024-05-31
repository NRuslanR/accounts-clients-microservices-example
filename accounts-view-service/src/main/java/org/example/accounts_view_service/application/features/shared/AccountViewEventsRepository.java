package org.example.accounts_view_service.application.features.shared;

import org.example.accounts_events.AccountCreated;

import reactor.core.publisher.Mono;

public interface AccountViewEventsRepository 
{
    Mono<AccountView> saveAccountCreated(AccountCreated accountCreated);
}
