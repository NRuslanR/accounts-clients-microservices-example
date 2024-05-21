package org.example.accounts_service.application.shared.fakes;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.example.accounts_service.application.shared.domain.events.AppDomainEvent;
import org.springframework.boot.test.context.TestComponent;
import org.springframework.context.annotation.Scope;

import io.eventuate.tram.events.common.DomainEvent;
import io.eventuate.tram.events.publisher.DomainEventPublisher;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

@Data
@TestComponent
@Scope("prototype")
public class FakeDomainEventPublisher implements DomainEventPublisher
{
    @Setter(AccessLevel.PRIVATE)
    private List<AppDomainEvent> eventsForPublishing = new ArrayList<>();

    @Override
    public void publish(
        String aggregateType, 
        Object aggregateId, 
        List<DomainEvent> domainEvents
    ) 
    {
        setEventsForPublishing(
            domainEvents.stream().map(v -> (AppDomainEvent)v).toList()
        );
    }

    @Override
    public void publish(
        String aggregateType, 
        Object aggregateId, 
        Map<String, String> headers,
        List<DomainEvent> domainEvents
    ) 
    {
        throw new UnsupportedOperationException("Unimplemented method 'publish'");
    }
}
