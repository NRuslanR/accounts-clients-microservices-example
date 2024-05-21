package org.example.accounts_service.application.accounts.features.creating;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.stream.Stream;

import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.util.StringUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@TestInstance(Lifecycle.PER_CLASS)
public abstract class CreateAccountTests 
{
    private final CreateAccount createAccount;

    @ParameterizedTest
    @MethodSource("createCommandsForAccountCreating")
    public void should_CreateAccount_When_CommandIsValid(CreateAccountCommand command)
    {
        var result = createAccount.run(command);

        assertNotNull(result);

        var account = result.getAccount();

        assertNotNull(account);
        assertTrue(StringUtils.hasText(account.getId()));
        assertEquals(command.getName(), account.getName());
        assertEquals(command.getAmount(), account.getAmount());
    }

    @ParameterizedTest
    @MethodSource("createIncorrectCommandsForAccountCreating")
    public void should_RaiseError_When_CreateAccountCommandIsInvalid(CreateAccountCommand invalidCommand)
    {
        assertThrows(CreateAccountException.class, () -> {

            createAccount.run(invalidCommand);

        });
    }
    
    private Stream<Arguments> createCommandsForAccountCreating()
    {
        return Stream.of(
            Arguments.of(CreateAccountCommand.of("#1", 0)),
            Arguments.of(CreateAccountCommand.of("#2", 345))
        );
    }

    private Stream<Arguments> createIncorrectCommandsForAccountCreating()
    {
        return Stream.of(
            Arguments.of(CreateAccountCommand.of(" ", 0)),
            Arguments.of(new CreateAccountCommand()) ,
            Arguments.of(CreateAccountCommand.of("#1", -1))
        );
    }
}
