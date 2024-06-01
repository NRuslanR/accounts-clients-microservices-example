package org.example.accounts_view_service.application.features.shared;

import java.time.Duration;

import org.example.accounts_events.AccountCreated;
import org.example.accounts_events.AccountCredited;
import org.example.accounts_events.AccountDebited;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
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
                    .is(accountCreated.getAggregateId().toString())
            );

        var accountViewUpdate =
            Update
                .update("name", accountCreated.getName())
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
            .retryWhen(createCommonRetry());
    }

    @Override
    public Mono<AccountView> saveAccountCredited(AccountCredited accountCredited) 
    {
        return saveAccountBalance(
            accountCredited.getAggregateId().toString(), 
            accountCredited.getBalance()
        );
    }  

    @Override
    public Mono<AccountView> saveAccountDebited(AccountDebited accountDebited) 
    {
        return saveAccountBalance(
            accountDebited.getAggregateId().toString(), 
            accountDebited.getBalance()
        );
    }

    private Mono<AccountView> saveAccountBalance(String accountId, int balance) 
    {
        var query =
            Query.query(
                Criteria
                    .where("accountId")
                    .is(accountId)
            );

        var update =
            Update.update("balance", balance);

        var options = FindAndModifyOptions.options().returnNew(true);

        return
            mongoTemplate
                .findAndModify(query, update, options, AccountView.class)
                .retryWhen(createCommonRetry());
    }

    private Retry createCommonRetry()
    {
        return 
            Retry
            .backoff(5, Duration.ofMillis(250))
            .jitter(0.75)
            .filter(e -> e instanceof DuplicateKeyException);
    }
}
