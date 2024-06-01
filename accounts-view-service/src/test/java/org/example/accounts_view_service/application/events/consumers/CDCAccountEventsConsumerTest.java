package org.example.accounts_view_service.application.events.consumers;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.example.accounts_view_service.application.shared.config.DisableMessagingAutoConfiguration;
import org.example.test_extensions.CDCConsumerTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@DisableMessagingAutoConfiguration
@CDCConsumerTest
@AutoConfigureStubRunner(ids = "org.example:accounts_service")
@ContextConfiguration(
    classes = AccountEventsConsumingConfig.class
)
@DirtiesContext
public @interface CDCAccountEventsConsumerTest 
{
    
}
