package org.example.accounts_view_service.application.features.get_account_view_by_id;

import org.example.accounts_view_service.application.features.shared.AccountViewNotFoundException;
import org.example.accounts_view_service.application.features.shared.AccountViewRepository;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "AccountsCache")
public class GetAccountViewByIdImpl implements GetAccountViewById 
{
    private final AccountViewRepository accountViewRepository;
    private final Validator validator;

    @Override
    @Cacheable(key = "#query.accountId")
    public Mono<GetAccountViewByIdResult> run(@Valid GetAccountViewByIdQuery query) 
    {
        return 
                ensureQueryIsValid(query)
                    .map(GetAccountViewByIdQuery::getAccountId)
                    .flatMap(accountViewRepository::findById)
                    .switchIfEmpty(Mono.error(AccountViewNotFoundException::new))
                    .map(GetAccountViewByIdResult::of)
                    .cache();
    }
    
    private Mono<GetAccountViewByIdQuery> ensureQueryIsValid(GetAccountViewByIdQuery query) 
    {
        var violations = validator.validate(query);

        if (!violations.isEmpty())
            return Mono.error(new ConstraintViolationException(violations));
        
        return Mono.just(query);
    } 
}
