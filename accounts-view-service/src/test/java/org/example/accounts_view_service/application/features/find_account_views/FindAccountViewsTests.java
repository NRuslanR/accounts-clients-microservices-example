package org.example.accounts_view_service.application.features.find_account_views;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.example.accounts_view_service.application.features.shared.AccountView;
import org.example.accounts_view_service.application.shared.data.generating.TestCreateAccountView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

@TestInstance(Lifecycle.PER_CLASS)
@RequiredArgsConstructor
public abstract class FindAccountViewsTests 
{
    private final FindAccountViews getAccountViews;
    private final TestCreateAccountView testCreateAccountView;

    @Test
    public void should_Return_AllAccountViews()
    {
        Flux<AccountView> createdAccountViewsFlux = createRandomAccountViews(10);

        Mono<Tuple2<List<AccountView>, FindAccountViewsResult>> queryResult =
            createdAccountViewsFlux
                .collectList()
                .zipWhen(v -> getAccountViews.run(FindAccountViewsQuery.of()));

        StepVerifier
            .create(queryResult)
            .assertNext(v -> {
                
                var createdAccountViews = v.getT1();
                var result = v.getT2();

                assertNotNull(result);

                var foundAccountViews = result.getAccounts();

                assertNotNull(foundAccountViews);
                
                assertTrue(foundAccountViews.containsAll(createdAccountViews));
            })
            .verifyComplete();
    }

    private Flux<AccountView> createRandomAccountViews(int count) 
    {
        return testCreateAccountView.createRandomAccountViews(count);
    }
}
