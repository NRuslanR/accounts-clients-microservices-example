package org.example.accounts_service.application.accounts.features;

public class AccountNotFoundException extends RuntimeException {

    public AccountNotFoundException()
    {
        super("Account not found");
    }

    public AccountNotFoundException(String message)
    {
        super(message);
    }
}
