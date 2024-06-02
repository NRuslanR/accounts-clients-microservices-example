package org.example.accounts_view_service.application.features.get_account_view_by_id;

import reactor.core.publisher.Mono;

public interface GetAccountViewByIdEndpoint 
{
    Mono<GetAccountViewByIdResult> run(GetAccountViewByIdQuery query);
}
