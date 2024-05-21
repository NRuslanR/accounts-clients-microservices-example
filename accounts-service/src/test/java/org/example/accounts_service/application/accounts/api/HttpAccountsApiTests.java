package org.example.accounts_service.application.accounts.api;

import java.util.UUID;
import java.util.stream.Stream;

import org.example.accounts_service.application.accounts.features.AccountDto;
import org.example.accounts_service.application.accounts.features.creating.CreateAccountCommand;
import org.example.accounts_service.application.accounts.features.deposit.DepositAccountCommand;
import org.example.accounts_service.application.accounts.features.withdrawal.WithdrawAccountCommand;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

@TestInstance(Lifecycle.PER_CLASS)
public abstract class HttpAccountsApiTests
{
    @SneakyThrows
    @ParameterizedTest
    @MethodSource("createAccountCommands")
    public void should_CreateAccount_When_RequestIsValid(CreateAccountCommand command)
    {
        var createAccountRequestBody = requestBodyForAccountCommand(command);

        var response = executeCreateAccountRequest(createAccountRequestBody);

        assertCreateAccountResponse(response, command);
    }

    @ParameterizedTest
    @MethodSource("createDepositAmounts")
    public void should_DepositAccount_When_RequestIsValid(int depositAmount)
    {
        var account = createRandomAccount();

        var command = DepositAccountCommand.of(account.getId(), depositAmount);

        var depositAccountRequestBody = requestBodyForAccountCommand(command);

        var response = executeDepositAccountRequest(depositAccountRequestBody, command);

        assertDepositAccountResponse(response, account, command);
    }

    @Test
    public void should_Return_BadResponse_When_AccountNotFound()
    {
        var command = DepositAccountCommand.of(UUID.randomUUID().toString(), 12);

        var depositAccountRequestBody = requestBodyForAccountCommand(command);

        var response = executeDepositAccountRequest(depositAccountRequestBody, command);

        assertDepositAccountNotFound(response, command);
    }

    @ParameterizedTest
    @MethodSource("withdrawAccountInputs")
    public void shoud_WithdrawAccount_When_RequestIsValid(int balance, int withdrawAmount)
    {
        var account = createAccount(balance);

        var command = WithdrawAccountCommand.of(account.getId(), withdrawAmount);

        var withdrawAccountRequestBody = requestBodyForAccountCommand(command);
        
        var response = executeWithdrawAccountRequest(withdrawAccountRequestBody, command);

        assertWithdrawAccountResponse(response, account, command);
    }

    @ParameterizedTest
    @MethodSource("invalidWithdrawAccountCommands")
    public void should_Return_BadResponse_When_WithdrawAccountRequestIsInvalid(WithdrawAccountCommand invalidCommand)
    {
        var withdrawAccountRequestBody = requestBodyForAccountCommand(invalidCommand);

        var response = executeWithdrawAccountRequest(withdrawAccountRequestBody, invalidCommand);

        assertBadWithdrawAccountResponse(response, invalidCommand);
    }

    protected abstract void assertBadWithdrawAccountResponse(Object response, WithdrawAccountCommand invalidCommand);

    protected abstract Object executeCreateAccountRequest(String createAccountRequestBody);

    protected abstract void assertCreateAccountResponse(Object response, CreateAccountCommand command);

    protected abstract void assertDepositAccountResponse(
        Object response, AccountDto account, DepositAccountCommand command);

    protected abstract void assertDepositAccountNotFound(Object response, DepositAccountCommand command);

    protected abstract Object executeDepositAccountRequest(String depositAccountRequestBody, DepositAccountCommand command);

    protected abstract Object executeWithdrawAccountRequest(String withdrawAccountRequestBody,
            WithdrawAccountCommand command);

    protected abstract void assertWithdrawAccountResponse(Object response, AccountDto account,
            WithdrawAccountCommand command);

    protected abstract String extractResponseBody(Object response);

    private Stream<Arguments> invalidWithdrawAccountCommands()
    {
        var account = createRandomAccount();

        return Stream.of(
            Arguments.of(WithdrawAccountCommand.of(account.getId(), account.getAmount() + 1))
        );
    }

    private Stream<Arguments> createAccountCommands()
    {
        return Stream.of(
            Arguments.of(CreateAccountCommand.of("#1", 0)),
            Arguments.of(CreateAccountCommand.of("#2", 12))
        );
    }

    private Stream<Arguments> createDepositAmounts()
    {
        return Stream.of(
            Arguments.of(12)
        );
    }

    private Stream<Arguments> withdrawAccountInputs()
    {
        return Stream.of(
            Arguments.of(32, 32),
            Arguments.of(54, 22)
        );
    }

    private AccountDto createRandomAccount()
    {
        return createAccount(0);
    }

    private AccountDto createAccount(int balance)
    {
        var response =
            executeCreateAccountRequest(
                requestBodyForAccountCommand(
                    CreateAccountCommand.of(UUID.randomUUID().toString(), balance)
                )
            );

        return jsonToAccountDto(extractResponseBody(response));
    }

    @SneakyThrows
    private String requestBodyForAccountCommand(Object command) 
    {
        return new ObjectMapper().writeValueAsString(command);
    }

    @SneakyThrows
    private AccountDto jsonToAccountDto(String json)
    {
        return new ObjectMapper().readValue(json, AccountDto.class);
    }
}
