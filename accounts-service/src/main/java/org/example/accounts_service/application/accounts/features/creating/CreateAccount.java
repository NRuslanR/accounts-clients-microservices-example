package org.example.accounts_service.application.accounts.features.creating;

public interface CreateAccount 
{
    CreateAccountResult run(CreateAccountCommand command) throws NullPointerException, CreateAccountException;
    void approve(ApproveCreationAccountCommand command);
    void reject(RejectCreationAccountCommand command);
} 
