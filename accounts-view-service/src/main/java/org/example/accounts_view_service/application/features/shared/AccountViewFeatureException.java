package org.example.accounts_view_service.application.features.shared;

public class AccountViewFeatureException extends RuntimeException 
{
    public AccountViewFeatureException()
    {
        super();
    }

    public AccountViewFeatureException(String message)
    {
        super(message);
    }
}
