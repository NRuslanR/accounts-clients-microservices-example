package org.example.accounts_view_service.application.features.find_account_views;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

public interface FindAccountViews 
{
    Mono<FindAccountViewsResult> run(@Valid FindAccountViewsQuery query);
}
