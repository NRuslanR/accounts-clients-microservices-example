package org.example.accounts_service.application.accounts.features.deposit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.UUID;
import java.util.stream.Stream;

import org.example.accounts_service.application.accounts.features.AccountNotFoundException;
import org.example.accounts_service.application.accounts.features.shared.data.generating.TestCreateAccount;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@TestInstance(Lifecycle.PER_CLASS)
public abstract class DepositAccountTests 
{
    private final TestCreateAccount testCreateAccount;
    private final DepositAccount depositAccount;

    @ParameterizedTest
    @MethodSource("createDepositAmounts")
    public void should_DepositAccount_When_CommandIsValid_And_AccountExists(int depositAmount)
    {
        var account = testCreateAccount.createRandomAccount();

        var command = DepositAccountCommand.of(account.getId(), depositAmount);
        
        var result = depositAccount.run(command);

        assertThat(result, is(notNullValue()));

        var changedAccount = result.getAccount();

        assertThat(changedAccount, is(notNullValue()));
        assertThat(changedAccount.getAmount(), is(equalTo(account.getAmount() + command.getDepositAmount())));
    }

    @Test
    public void should_ThrowException_When_AccountNotFound()
    {
        assertThrows(AccountNotFoundException.class, () -> {

            depositAccount.run(DepositAccountCommand.of(UUID.randomUUID().toString(), 0));
        });
    }

    @ParameterizedTest
    @MethodSource("createInvalidDepositAccountCommands")
    public void should_ThrowException_When_DepositCommandInvalid(DepositAccountCommand invalidCommand)
    {
        assertThrows(DepositAccountException.class, () -> {

            depositAccount.run(invalidCommand);

        });
    }

    private Stream<Arguments> createDepositAmounts()
    {
        return Stream.of(
            Arguments.of(34),
            Arguments.of(1)
        );
    }

    private Stream<Arguments> createInvalidDepositAccountCommands()
    {
        var randomAccount = testCreateAccount.createRandomAccount();

        return Stream.of(
            Arguments.of(DepositAccountCommand.of(randomAccount.getId(), 0)),
            Arguments.of(DepositAccountCommand.of(randomAccount.getId(), -1))
        );
    }
}
