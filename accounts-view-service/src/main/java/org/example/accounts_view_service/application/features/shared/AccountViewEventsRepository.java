package org.example.accounts_view_service.application.features.shared;

import org.example.accounts_events.AccountApproved;
import org.example.accounts_events.AccountCreated;
import org.example.accounts_events.AccountCredited;
import org.example.accounts_events.AccountDebited;
import org.example.accounts_events.AccountRejected;

import reactor.core.publisher.Mono;

public interface AccountViewEventsRepository 
{
    Mono<AccountView> saveAccountCreated(AccountCreated accountCreated);
    Mono<AccountView> saveAccountApproved(AccountApproved accountApproved);
    Mono<AccountView> saveAccountRejected(AccountRejected accountRejected);
    Mono<AccountView> saveAccountCredited(AccountCredited accountCredited);
    Mono<AccountView> saveAccountDebited(AccountDebited accountDebited);
}
