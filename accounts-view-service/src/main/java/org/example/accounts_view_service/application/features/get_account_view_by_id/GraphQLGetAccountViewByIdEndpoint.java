package org.example.accounts_view_service.application.features.get_account_view_by_id;

import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Mono;

@Controller
public class GraphQLGetAccountViewByIdEndpoint extends AbstractGetAccountViewByIdEndpoint 
{
    public GraphQLGetAccountViewByIdEndpoint(GetAccountViewById getAccountViewById) 
    {
        super(getAccountViewById);
    }

    @Override
    @QueryMapping("getAccountById")
    public Mono<GetAccountViewByIdResult> run(
        @Argument("getAccountByIdInput") GetAccountViewByIdQuery query
    ) 
    {
        return super.run(query);
    }
}
