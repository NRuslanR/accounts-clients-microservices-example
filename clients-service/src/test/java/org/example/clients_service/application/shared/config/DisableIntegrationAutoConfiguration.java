package org.example.clients_service.application.shared.config;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.example.keycloak_roles_converters.KeycloakUserGrantedAuthoritiesConverterAutoConfig;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import io.eventuate.tram.sagas.spring.participant.autoconfigure.SpringParticipantAutoConfiguration;
import io.eventuate.tram.spring.consumer.jdbc.TramConsumerJdbcAutoConfiguration;
import io.eventuate.tram.spring.events.autoconfigure.TramEventsPublisherAutoConfiguration;
import io.eventuate.tram.spring.events.common.TramEventsCommonAutoConfiguration;
import io.eventuate.tram.spring.messaging.autoconfigure.TramMessageProducerJdbcAutoConfiguration;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
@EnableAutoConfiguration(exclude = {
    SpringParticipantAutoConfiguration.class,
    KeycloakUserGrantedAuthoritiesConverterAutoConfig.class,
    TramMessageProducerJdbcAutoConfiguration.class,
    TramEventsCommonAutoConfiguration.class,
    TramEventsPublisherAutoConfiguration.class,
    TramConsumerJdbcAutoConfiguration.class
})
public @interface DisableIntegrationAutoConfiguration {
    
}
