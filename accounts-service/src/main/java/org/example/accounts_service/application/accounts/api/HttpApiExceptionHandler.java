package org.example.accounts_service.application.accounts.api;

import org.example.accounts_service.application.accounts.features.AccountNotFoundException;
import org.example.accounts_service.application.accounts.features.creating.CreateAccountException;
import org.example.accounts_service.application.accounts.features.deposit.DepositAccountException;
import org.example.accounts_service.application.accounts.features.withdrawal.WithdrawAccountException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class HttpApiExceptionHandler extends ResponseEntityExceptionHandler 
{
    @ExceptionHandler({
        NullPointerException.class,
        CreateAccountException.class,
        DepositAccountException.class,
        WithdrawAccountException.class
    })
    public ResponseEntity<ApiError> handleAccountFeatureException(Exception exception)
    {
        return ResponseEntity.badRequest().body(ApiError.of(exception.getMessage()));
    }

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ApiError> handleAccountNotFoundException(Exception exception)
    {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiError.of(exception.getMessage()));
    }
}
