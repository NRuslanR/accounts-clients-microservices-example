package org.example.accounts_view_service.application.features.find_account_views;

import org.example.accounts_view_service.application.features.shared.AccountViewRepository;
import org.springframework.stereotype.Service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class FindAccountViewsImpl implements FindAccountViews 
{
    private final AccountViewRepository accountViewRepository;
    
    @Override
    public Mono<FindAccountViewsResult> run(@Valid FindAccountViewsQuery query) 
    {
        return 
            accountViewRepository
                .findAll()
                .collectList()
                .map(FindAccountViewsResult::of);
    }
}
