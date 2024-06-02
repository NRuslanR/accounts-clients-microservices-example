package org.example.accounts_view_service.application.features.find_account_views;

import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import reactor.core.publisher.Mono;

@Controller
public class GraphQLFindAccountViewsEndpoint extends AbstractFindAccountViewsEndpoint
{
    public GraphQLFindAccountViewsEndpoint(FindAccountViews findAccountViews) 
    {
        super(findAccountViews);
    }

    @Override
    @QueryMapping("findAllAccounts")
    public Mono<FindAccountViewsResult> findAllAccountViews()
    {
        return super.findAllAccountViews();
    }
}
