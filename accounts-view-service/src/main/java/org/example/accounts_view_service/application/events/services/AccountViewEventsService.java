package org.example.accounts_view_service.application.events.services;

import org.example.accounts_events.AccountCreated;
import org.example.accounts_view_service.application.features.shared.AccountView;

import reactor.core.publisher.Mono;

public interface AccountViewEventsService 
{
    Mono<AccountView> applyAccountCreated(AccountCreated accountCreated);
}
