package org.example.accounts_service.application.accounts.features.withdrawal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.Stream;

import org.example.accounts_service.application.accounts.features.shared.data.generating.TestCreateAccount;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@TestInstance(Lifecycle.PER_CLASS)
public abstract class WithdrawAccountTests {
    
    private final TestCreateAccount testCreateAccount;
    private final WithdrawAccount withdrawAccount;

    @ParameterizedTest
    @MethodSource("withdrawAmounts")
    public void should_WithdrawAccount_When_CommandIsValid_And_AccountExists(
        int accountBalance, int withdrawAmount)
    {
        var account = testCreateAccount.createAccount(accountBalance);
        
        var command = WithdrawAccountCommand.of(account.getId(), withdrawAmount);

        var result = withdrawAccount.run(command);

        assertNotNull(result);

        var withdrawedAccount = result.getAccount();

        assertNotNull(withdrawedAccount);

        assertThat(withdrawedAccount.getId(), is(equalTo(account.getId())));
        assertThat(withdrawedAccount.getAmount(), is(equalTo(account.getAmount() - command.getWithdrawAmount())));
    }

    @ParameterizedTest
    @MethodSource("invalidWithdrawAccountCommand")
    public void should_ThrowException_When_CommandIsInvalid(WithdrawAccountCommand invalidCommand)
    {
        assertThrows(WithdrawAccountException.class, () -> {
            
            withdrawAccount.run(invalidCommand);

        });
    }

    private Stream<Arguments> withdrawAmounts()
    {
        return Stream.of(
            Arguments.of(100, 50),
            Arguments.of(34, 21),
            Arguments.of(20, 20)
        );
    }

    private Stream<Arguments> invalidWithdrawAccountCommand()
    {
        var account = testCreateAccount.createRandomAccount();

        return Stream.of(
            Arguments.of(WithdrawAccountCommand.of(account.getId(), 0)),
            Arguments.of(WithdrawAccountCommand.of(account.getId(), -10)),
            Arguments.of(WithdrawAccountCommand.of(account.getId(), account.getAmount() + 1))
        );
    }
}
