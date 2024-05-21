package org.example.accounts_service.application.accounts.features.deposit;

public class DepositAccountException extends RuntimeException 
{
    public DepositAccountException()
    {
        super();
    }

    public DepositAccountException(String message)
    {
        super(message);
    }
}
