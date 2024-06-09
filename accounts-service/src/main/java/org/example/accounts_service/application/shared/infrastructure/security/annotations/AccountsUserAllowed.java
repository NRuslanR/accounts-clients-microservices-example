package org.example.accounts_service.application.shared.infrastructure.security.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.security.access.prepost.PreAuthorize;

@Retention(RetentionPolicy.RUNTIME)
@Target({
    ElementType.TYPE,
    ElementType.METHOD
})
@Inherited
@PreAuthorize("hasAuthority('ACCOUNTING')")
public @interface AccountsUserAllowed 
{
    
}
