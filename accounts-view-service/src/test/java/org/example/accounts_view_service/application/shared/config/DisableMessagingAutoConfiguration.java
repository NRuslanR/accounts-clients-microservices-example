package org.example.accounts_view_service.application.shared.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import io.eventuate.tram.spring.consumer.jdbc.TramConsumerJdbcAutoConfiguration;
import io.eventuate.tram.spring.events.autoconfigure.TramEventsPublisherAutoConfiguration;
import io.eventuate.tram.spring.events.autoconfigure.TramEventsSubscriberAutoConfiguration;
import io.eventuate.tram.spring.events.common.TramEventsCommonAutoConfiguration;
import io.eventuate.tram.spring.messaging.autoconfigure.EventuateTramKafkaMessageConsumerAutoConfiguration;
import io.eventuate.tram.spring.messaging.autoconfigure.TramMessageProducerJdbcAutoConfiguration;
import io.eventuate.tram.spring.messaging.common.TramMessagingCommonAutoConfiguration;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@EnableAutoConfiguration(exclude = {
    TramMessageProducerJdbcAutoConfiguration.class,
    TramEventsSubscriberAutoConfiguration.class,
    TramEventsCommonAutoConfiguration.class,
    TramEventsPublisherAutoConfiguration.class,
    TramConsumerJdbcAutoConfiguration.class,
    TramEventsPublisherAutoConfiguration.class,
    TramMessagingCommonAutoConfiguration.class,
    EventuateTramKafkaMessageConsumerAutoConfiguration.class
})
public @interface DisableMessagingAutoConfiguration {
    
}
