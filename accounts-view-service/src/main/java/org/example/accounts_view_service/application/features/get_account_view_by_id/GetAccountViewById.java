package org.example.accounts_view_service.application.features.get_account_view_by_id;

import jakarta.validation.Valid;
import reactor.core.publisher.Mono;

public interface GetAccountViewById 
{
    Mono<GetAccountViewByIdResult> run(@Valid Mono<GetAccountViewByIdQuery> query);
}
