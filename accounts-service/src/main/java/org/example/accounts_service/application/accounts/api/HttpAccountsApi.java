package org.example.accounts_service.application.accounts.api;

import org.example.accounts_service.application.accounts.features.creating.CreateAccountCommand;
import org.example.accounts_service.application.accounts.features.creating.CreateAccountResult;
import org.example.accounts_service.application.accounts.features.creating.saga.CreateAccountSagaService;
import org.example.accounts_service.application.accounts.features.deposit.DepositAccount;
import org.example.accounts_service.application.accounts.features.deposit.DepositAccountCommand;
import org.example.accounts_service.application.accounts.features.deposit.DepositAccountResult;
import org.example.accounts_service.application.accounts.features.withdrawal.WithdrawAccount;
import org.example.accounts_service.application.accounts.features.withdrawal.WithdrawAccountCommand;
import org.example.accounts_service.application.accounts.features.withdrawal.WithdrawAccountResult;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(path = "/api/accounts")
public class HttpAccountsApi extends AbstractAccountsApi
{
    public HttpAccountsApi(
        CreateAccountSagaService createAccountSagaService, 
        DepositAccount depositAccount,
        WithdrawAccount withdrawAccount
    ) 
    {
        super(createAccountSagaService, depositAccount, withdrawAccount);
    }

    @Override
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public CreateAccountResult createAccount(@RequestBody CreateAccountCommand command) 
    {
        return super.createAccount(command);
    }

    @Override
    @PostMapping(path = "/{accountId}/deposit")
    public DepositAccountResult depositAccount(
        @PathVariable("accountId") String accountId, 
        @RequestBody DepositAccountCommand command
    ) 
    {
        return super.depositAccount(accountId, command);
    }

    @Override
    @PostMapping(path = "/{accountId}/withdraw")
    public WithdrawAccountResult withdrawAccount(
        @PathVariable("accountId") String accountId, 
        @RequestBody WithdrawAccountCommand command
    ) 
    {
        return super.withdrawAccount(accountId, command);
    }
}
