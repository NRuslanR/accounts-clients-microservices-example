package org.example.accounts_events;

import java.util.UUID;

import io.eventuate.tram.events.common.DomainEvent;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public abstract class AppDomainEvent implements DomainEvent
{
    @NonNull
    private UUID id;
    
    @NonNull
    private UUID aggregateId;
}
