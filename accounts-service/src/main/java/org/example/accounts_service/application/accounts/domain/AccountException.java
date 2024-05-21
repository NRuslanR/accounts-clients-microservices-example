package org.example.accounts_service.application.accounts.domain;

public class AccountException extends RuntimeException 
{
    public AccountException()
    {
        super();
    }

    public AccountException(String message)
    {
        super(message);
    }
}
