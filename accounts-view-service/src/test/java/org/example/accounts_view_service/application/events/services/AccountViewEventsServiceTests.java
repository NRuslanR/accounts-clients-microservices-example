package org.example.accounts_view_service.application.events.services;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertNotNull;

import java.util.UUID;
import java.util.stream.Stream;

import org.example.accounts_events.AccountCreated;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lombok.RequiredArgsConstructor;
import reactor.test.StepVerifier;

@TestInstance(Lifecycle.PER_CLASS)
@RequiredArgsConstructor
public abstract class AccountViewEventsServiceTests 
{
    private final AccountViewEventsService accountViewEventsService;

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

    private Stream<Arguments> accountCreatedInstances()
    {
        return Stream.of(
            Arguments.of(AccountCreated.of(UUID.randomUUID(), UUID.randomUUID(), "#1", 0)),
            Arguments.of(AccountCreated.of(UUID.randomUUID(), UUID.randomUUID(), "#2", 3231))
        );
    }
}
