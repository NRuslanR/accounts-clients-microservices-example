package org.example.accounts_view_service.application.features.shared;

import java.time.Duration;

import org.example.accounts_events.AccountCreated;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.mongodb.DuplicateKeyException;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

@RequiredArgsConstructor
public class AccountViewEventsRepositoryImpl implements AccountViewEventsRepository 
{
    private final ReactiveMongoTemplate mongoTemplate;

    @Override
    public Mono<AccountView> saveAccountCreated(AccountCreated accountCreated) 
    {
        var accountViewQuery = 
            Query.query(
                Criteria
                    .where("accountId")
                    .is(accountCreated.getAggregateId())
            );

        var accountViewUpdate =
            Update
                .update("accountId", accountCreated.getAggregateId())
                .set("name", accountCreated.getName())
                .set("balance", accountCreated.getAmount());

        return
            mongoTemplate.upsert(
                accountViewQuery, 
                accountViewUpdate,
                AccountView.class
            )
            .flatMap(
                v -> 
                    mongoTemplate
                        .findOne(
                            accountViewQuery, 
                            AccountView.class
                        )
            )
            .retryWhen(
                Retry
                    .backoff(5, Duration.ofMillis(250))
                    .jitter(0.75)
                    .filter(e -> e instanceof DuplicateKeyException)
            );
    }  
}
