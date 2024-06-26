package org.example.accounts_service.application.accounts.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.UUID;
import java.util.stream.Stream;

import org.example.accounts_events.AccountCreated;
import org.example.test_extensions.UnitTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

@UnitTest
public class AccountTests 
{
   private static Stream<Arguments> accountCreationParams()
   {
        return Stream.of(
            Arguments.of(UUID.randomUUID(), "#1", 0, "#client1"),
            Arguments.of(UUID.randomUUID(), "#2", 23, "#client1")
        );
   }

   private static Stream<Arguments> incorrectAccountNames()
   {
        return Stream.of(
            Arguments.of(" "),
            Arguments.of("")
        );
   }

   @ParameterizedTest
   @MethodSource("accountCreationParams")
   public void should_CreateAccount_When_InvariantsSatisfied(UUID id, String name, int amount, String clientId)
   {
        var accountWithEvents = Account.of(id, name, amount, clientId);

        var account = accountWithEvents.result;

        assertEquals(id, account.getId());  
        assertEquals(name, account.getName());
        assertEquals(amount, account.getAmount());
        assertEquals(clientId, account.getClientId());
        assertEquals(AccountStatus.Pending, account.getStatus());

        var events = accountWithEvents.events;

        assertEquals(1, events.size());

        var event = events.get(0);

        assertTrue(event instanceof AccountCreated);

        var accountCreatedEvent = (AccountCreated)event;

        assertNotNull(accountCreatedEvent.getId());
        assertEquals(id, accountCreatedEvent.getAggregateId());
        assertEquals(name, accountCreatedEvent.getName());
        assertEquals(amount, accountCreatedEvent.getAmount());
        assertEquals(clientId, accountCreatedEvent.getClientId());
        assertEquals(AccountStatus.Pending.toString(), accountCreatedEvent.getStatus());
   }

   @ParameterizedTest
   @MethodSource("incorrectAccountNames")
   public void should_ThrowException_When_AccountName_Is_InCorrect_To_Be_Changed(String incorrectName)
   {
        var account = createRandomAccount();

        assertThrows(AccountException.class, () -> {

            account.setName(incorrectName);

        });
   }

   private Account createRandomAccount()
   {
        return Account.of(UUID.randomUUID(), "#1", 0, "#client1").result;
   }
}
