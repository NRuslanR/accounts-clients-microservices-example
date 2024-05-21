package org.example.accounts_service.application.shared.config;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

import io.eventuate.tram.spring.consumer.jdbc.TramConsumerJdbcAutoConfiguration;
import io.eventuate.tram.spring.events.autoconfigure.TramEventsPublisherAutoConfiguration;
import io.eventuate.tram.spring.events.common.TramEventsCommonAutoConfiguration;
import io.eventuate.tram.spring.messaging.autoconfigure.TramMessageProducerJdbcAutoConfiguration;

@UnitTest
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@EnableAutoConfiguration(exclude = {
    DataSourceAutoConfiguration.class,
    TramMessageProducerJdbcAutoConfiguration.class,
    TramEventsCommonAutoConfiguration.class,
    TramEventsPublisherAutoConfiguration.class,
    TramConsumerJdbcAutoConfiguration.class
})
public @interface UnitApiTest 
{
    
}
