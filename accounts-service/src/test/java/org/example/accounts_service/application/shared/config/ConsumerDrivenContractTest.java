package org.example.accounts_service.application.shared.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.cloud.contract.verifier.messaging.boot.AutoConfigureMessageVerifier;
import org.springframework.context.annotation.Import;

import io.eventuate.tram.spring.consumer.jdbc.TramConsumerJdbcAutoConfiguration;
import io.eventuate.tram.spring.events.autoconfigure.TramEventsPublisherAutoConfiguration;
import io.eventuate.tram.spring.events.common.TramEventsCommonAutoConfiguration;
import io.eventuate.tram.spring.messaging.autoconfigure.TramMessageProducerJdbcAutoConfiguration;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@AutoConfigureMessageVerifier
@Import(ConsumerDrivenContractTestConfig.class)
@EnableAutoConfiguration(exclude = {
    DataSourceAutoConfiguration.class,
    TramMessageProducerJdbcAutoConfiguration.class,
    TramEventsCommonAutoConfiguration.class,
    TramEventsPublisherAutoConfiguration.class,
    TramConsumerJdbcAutoConfiguration.class
})
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = "(.*)cdc(.*)")
public @interface ConsumerDrivenContractTest 
{
    
}
