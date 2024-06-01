package org.example.accounts_view_service.application.events.consumers;

import static org.awaitility.Awaitility.await;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;

import org.example.accounts_events.AccountCreated;
import org.example.accounts_events.AccountCredited;
import org.example.accounts_events.AccountDebited;
import org.example.accounts_view_service.application.events.services.AccountViewEventsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.StubTrigger;

@CDCAccountEventsConsumerTest
@TestInstance(Lifecycle.PER_CLASS)
public class MessagingContractAccountEventsConsumerTests 
{
    @Autowired
    private StubTrigger stubTrigger;

    @MockBean
    private AccountViewEventsService accountViewEventsService;

    @Test
    public void shouldHandleAccountCreatedEvent()
    {
        stubTrigger.trigger("triggerAccountCreatedEvent");

        await().untilAsserted(() -> {

            verify(accountViewEventsService)
                .applyAccountCreated(any(AccountCreated.class));
        });
    }

    @Test
    public void shouldHandleAccountCreditedEvent()
    {
        stubTrigger.trigger("triggerAccountCreditedEvent");

        await().untilAsserted(() -> {

            verify(accountViewEventsService)
                .applyAccountCredited(any(AccountCredited.class));
        });
    }

    @Test
    public void shouldHandleAccountDebitedEvent()
    {
        stubTrigger.trigger("triggerAccountDebitedEvent");

        await().untilAsserted(() -> {

            verify(accountViewEventsService)
                .applyAccountDebited(any(AccountDebited.class));
        });
    }
}
