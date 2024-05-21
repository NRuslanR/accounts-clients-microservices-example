package org.example.accounts_service.application.accounts.features.withdrawal;

public class WithdrawAccountException extends RuntimeException {

    public WithdrawAccountException()
    {
        super("Failed to withdraw account");
    }

    public WithdrawAccountException(String message)
    {
        super(message);
    }
}
