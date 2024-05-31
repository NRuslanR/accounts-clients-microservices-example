package org.example.accounts_view_service.application.features.get_account_view_by_id;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.stream.Stream;

import org.apache.kafka.common.Uuid;
import org.example.accounts_view_service.application.features.shared.AccountView;
import org.example.accounts_view_service.application.features.shared.AccountViewNotFoundException;
import org.example.accounts_view_service.application.shared.data.generating.TestCreateAccountView;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import jakarta.validation.ConstraintViolationException;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

@TestInstance(Lifecycle.PER_CLASS)
@RequiredArgsConstructor
public abstract class GetAccountViewByIdTests 
{
    private final GetAccountViewById getAccountViewById;
    private final TestCreateAccountView testCreateAccountView;
    
    @ParameterizedTest
    @MethodSource("createAccountViews")
    public void should_Get_Account_When_QueryIsValid_And_AccountExists(
        Mono<AccountView> createdAccountViewMono
    )
    {
        Mono<Tuple2<AccountView, GetAccountViewByIdResult>> queryResult =
            createdAccountViewMono
                .zipWhen(
                    v -> 
                        getAccountViewById
                            .run(
                                Mono.just(
                                    GetAccountViewByIdQuery.of(v.getAccountId())
                                )
                            )
                );
        
        StepVerifier
            .create(queryResult)
            .assertNext(v -> {

                var createdAccountView = v.getT1();
                var result = v.getT2();
                
                assertNotNull(result);

                var fetchedAccountView = result.getAccountView();

                assertNotNull(fetchedAccountView);

                assertThat(fetchedAccountView.getAccountId(), is(equalTo(createdAccountView.getAccountId())));
                assertThat(fetchedAccountView.getName(), is(equalTo(createdAccountView.getName())));
                assertThat(fetchedAccountView.getBalance(), is(equalTo(createdAccountView.getBalance())));
            })
            .verifyComplete();
    }

    @ParameterizedTest
    @MethodSource("createIncorrectQueries")
    public void should_Raise_Error_When_QueryIsInvalid(Mono<GetAccountViewByIdQuery> invalidQuery)
    {
        var result =
            invalidQuery
                .map(Mono::just)
                .flatMap(getAccountViewById::run);

        StepVerifier
            .create(result)
            .expectError(ConstraintViolationException.class)
            .verify();
    }

    @Test
    public void should_Raise_Error_When_AccountViewNotFound()
    {
        var query = GetAccountViewByIdQuery.of(Uuid.randomUuid().toString());

        var result = getAccountViewById.run(Mono.just(query));

        StepVerifier
            .create(result)
            .expectError(AccountViewNotFoundException.class)
            .verify();
    }

    private Stream<Arguments> createAccountViews()
    {
        return Stream.of(
            Arguments.of(testCreateAccountView.createRandomAccountView()),
            Arguments.of(testCreateAccountView.createRandomAccountView())
        );
    }

    private Stream<Arguments> createIncorrectQueries()
    {
        return Stream.of(
            Arguments.of(Mono.just(GetAccountViewByIdQuery.of(null)),
            Arguments.of(Mono.just(GetAccountViewByIdQuery.of(" "))))
        );
    }

}
