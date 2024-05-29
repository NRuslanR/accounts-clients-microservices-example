package org.example.accounts_view_service.application.features.shared;

public class AccountViewNotFoundException extends AccountViewFeatureException 
{
    public AccountViewNotFoundException()
    {
        super("Account not found");
    }

    public AccountViewNotFoundException(String message)
    {
        super(message);
    }
}
