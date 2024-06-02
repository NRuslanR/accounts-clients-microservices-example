package org.example.accounts_view_service.application.features.find_account_views;

import reactor.core.publisher.Mono;

public interface FindAccountViewsEndpoint 
{
    Mono<FindAccountViewsResult> findAllAccountViews();
}
