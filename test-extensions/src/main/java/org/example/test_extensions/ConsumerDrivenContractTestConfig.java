package org.example.test_extensions;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

import io.eventuate.tram.spring.cloudcontractsupport.EventuateContractVerifierConfiguration;
import io.eventuate.tram.spring.events.publisher.TramEventsPublisherConfiguration;
import io.eventuate.tram.spring.inmemory.TramInMemoryConfiguration;

@TestConfiguration
@Import({
    TramEventsPublisherConfiguration.class,
    TramInMemoryConfiguration.class,
    EventuateContractVerifierConfiguration.class
})
public class ConsumerDrivenContractTestConfig {
    
}
