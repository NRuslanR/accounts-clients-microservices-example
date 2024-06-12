package org.example.accounts_view_service.application.events.services;

import org.example.accounts_events.AccountApproved;
import org.example.accounts_events.AccountCreated;
import org.example.accounts_events.AccountCredited;
import org.example.accounts_events.AccountDebited;
import org.example.accounts_events.AccountRejected;
import org.example.accounts_view_service.application.features.shared.AccountView;

import reactor.core.publisher.Mono;

public interface AccountViewEventsService 
{
    Mono<AccountView> applyAccountCreated(AccountCreated accountCreated);
    Mono<AccountView> applyAccountApproved(AccountApproved accountApproved);
    Mono<AccountView> applyAccountRejected(AccountRejected accountRejected);
    Mono<AccountView> applyAccountCredited(AccountCredited accountCredited);
    Mono<AccountView> applyAccountDebited(AccountDebited accountDebited);
}
