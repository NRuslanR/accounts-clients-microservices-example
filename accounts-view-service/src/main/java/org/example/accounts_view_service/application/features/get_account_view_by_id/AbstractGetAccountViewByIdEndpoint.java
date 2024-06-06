package org.example.accounts_view_service.application.features.get_account_view_by_id;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RequiredArgsConstructor
public abstract class AbstractGetAccountViewByIdEndpoint implements GetAccountViewByIdEndpoint 
{
    private final GetAccountViewById getAccountViewById;

    @Override
    public Mono<GetAccountViewByIdResult> run(GetAccountViewByIdQuery query) 
    {
        return getAccountViewById.run(query);
    }
    
}
