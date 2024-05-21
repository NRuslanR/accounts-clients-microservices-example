package org.example.accounts_service.application.accounts.features.creating;

public class CreateAccountException extends RuntimeException 
{
    public CreateAccountException()
    {
        super();
    }    

    public CreateAccountException(String message)
    {
        super(message);
    }
}
