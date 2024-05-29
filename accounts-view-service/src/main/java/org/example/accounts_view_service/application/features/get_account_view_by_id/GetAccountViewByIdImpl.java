package org.example.accounts_view_service.application.features.get_account_view_by_id;

import org.example.accounts_view_service.application.features.shared.AccountViewNotFoundException;
import org.example.accounts_view_service.application.features.shared.AccountViewRepository;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class GetAccountViewByIdImpl implements GetAccountViewById 
{
    private final AccountViewRepository accountViewRepository;

    @Override
    public Mono<GetAccountViewByIdResult> run(@Valid Mono<GetAccountViewByIdQuery> query) 
    {
        return 
            query
                .map(GetAccountViewByIdQuery::getAccountId)
                .flatMap(accountViewRepository::findById)
                .switchIfEmpty(Mono.error(AccountViewNotFoundException::new))
                .map(GetAccountViewByIdResult::of);
    }
    
}
