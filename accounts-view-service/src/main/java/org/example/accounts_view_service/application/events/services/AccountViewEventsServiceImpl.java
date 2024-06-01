package org.example.accounts_view_service.application.events.services;

import org.example.accounts_events.AccountCreated;
import org.example.accounts_events.AccountCredited;
import org.example.accounts_events.AccountDebited;
import org.example.accounts_view_service.application.features.shared.AccountView;
import org.example.accounts_view_service.application.features.shared.AccountViewRepository;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class AccountViewEventsServiceImpl implements AccountViewEventsService 
{
    private final AccountViewRepository eventsRepository;

    @Override
    public Mono<AccountView> applyAccountCreated(AccountCreated accountCreated) 
    {
        return eventsRepository.saveAccountCreated(accountCreated);
    }

    @Override
    public Mono<AccountView> applyAccountCredited(AccountCredited accountCredited) 
    {
        return eventsRepository.saveAccountCredited(accountCredited);
    }

    @Override
    public Mono<AccountView> applyAccountDebited(AccountDebited accountDebited) 
    {
        return eventsRepository.saveAccountDebited(accountDebited);
    }
}
