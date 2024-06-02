package org.example.accounts_view_service.application.features.find_account_views;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public abstract class AbstractFindAccountViewsEndpoint implements FindAccountViewsEndpoint 
{
    private final FindAccountViews findAccountViews;

    @Override
    public Mono<FindAccountViewsResult> findAllAccountViews() 
    {
        return findAccountViews.run(FindAccountViewsQuery.of());
    }
}
