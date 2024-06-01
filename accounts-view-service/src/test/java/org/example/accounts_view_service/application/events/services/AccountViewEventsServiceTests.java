package org.example.accounts_view_service.application.events.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

import java.util.Random;
import java.util.UUID;
import java.util.function.Function;
import java.util.stream.Stream;

import org.example.accounts_events.AccountCreated;
import org.example.accounts_events.AccountCredited;
import org.example.accounts_events.AppDomainEvent;
import org.example.accounts_view_service.application.features.shared.AccountView;
import org.example.accounts_view_service.application.shared.data.generating.TestCreateAccountView;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;

@TestInstance(Lifecycle.PER_CLASS)
@RequiredArgsConstructor
public abstract class AccountViewEventsServiceTests 
{
    private final AccountViewEventsService accountViewEventsService;
    private final TestCreateAccountView testCreateAccountView;

    @ParameterizedTest
    @MethodSource("accountCreatedInstances")
    public void should_Apply_AccountCreatedEvent(AccountCreated accountCreated)
    {
        var result =
            accountViewEventsService
                .applyAccountCreated(accountCreated);
                
        StepVerifier
            .create(result)
            .assertNext(v -> {

                assertNotNull(v);

                assertThat(v.getAccountId(), is(equalTo(accountCreated.getAggregateId().toString())));
                assertThat(v.getBalance(), is(equalTo(accountCreated.getAmount())));
                assertThat(v.getName(), is(equalTo(accountCreated.getName())));     
            })
            .verifyComplete();
    }

    @ParameterizedTest
    @MethodSource("accountViewsToBeCredited")
    public void should_Apply_AccountCreditedEvent(Mono<AccountView> createdAccountView)
    {
        var balance = new Random().ints(22, 50).boxed().findFirst().get();
        var depositAmount = new Random().ints(5, 13).boxed().findFirst().get();

        should_Apply_AccountBalanceChanged(
            createdAccountView, 
            accountId ->
                AccountCredited.of(
                    UUID.randomUUID(), 
                    UUID.fromString(accountId), 
                    balance, 
                    depositAmount
                )
            ,
            accountViewEventsService::applyAccountCredited 
        );
    }

    private Stream<Arguments> accountViewsToBeCredited()
    {
        return Stream.of(
            Arguments.of(testCreateAccountView.createRandomAccountView()),
            Arguments.of(testCreateAccountView.createRandomAccountView())
        );
    }

    @SneakyThrows
    private <TEvent extends AppDomainEvent> void should_Apply_AccountBalanceChanged(
        Mono<AccountView> createdAccountView, 
        Function<String, TEvent> eventSupplier,
        Function<TEvent, Mono<? extends AccountView>> updatedAccountViewSupplier
    )
    {
        Mono<Tuple2<TEvent, AccountView>> result =
            createdAccountView
                .map(AccountView::getAccountId)
                .map(eventSupplier)
                .zipWhen(updatedAccountViewSupplier);

        StepVerifier
            .create(result)
            .assertNext(this::assertAccountBalanceChanged)
            .verifyComplete();
    }

    @SneakyThrows
    private <TEvent extends AppDomainEvent> void assertAccountBalanceChanged(
        Tuple2<TEvent, AccountView> result
    )
    {
        var accountBalanceChanged = result.getT1();
        var accountView = result.getT2();

        assertNotNull(accountView);

        var balance = (int)accountBalanceChanged.getClass().getMethod("getBalance").invoke(accountBalanceChanged);

        assertThat(accountView.getAccountId(), is(equalTo(accountBalanceChanged.getAggregateId().toString())));
        assertThat(accountView.getBalance(), is(equalTo(balance)));
    }

    private Stream<Arguments> accountCreatedInstances()
    {
        return Stream.of(
            Arguments.of(AccountCreated.of(UUID.randomUUID(), UUID.randomUUID(), "#1", 0)),
            Arguments.of(AccountCreated.of(UUID.randomUUID(), UUID.randomUUID(), "#2", 3231))
        );
    }
}
