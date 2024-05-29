package org.example.accounts_view_service.application.features.get_account_view_by_id;

import reactor.core.publisher.Mono;

public interface GetAccountViewById 
{
    Mono<GetAccountViewByIdResult> run(Mono<GetAccountViewByIdQuery> query);
}
