package org.example.accounts_service.application.accounts.domain;

import java.util.List;

import io.eventuate.tram.events.common.DomainEvent;
import io.eventuate.tram.events.publisher.ResultWithEvents;

public class AccountWithEvents extends ResultWithEvents<Account> 
{
    public AccountWithEvents(Account result, List<DomainEvent> events) 
    {
        super(result, events);
    }
  
    public AccountWithEvents(Account result, DomainEvent... events) 
    {
        super(result, events);
    }
}
