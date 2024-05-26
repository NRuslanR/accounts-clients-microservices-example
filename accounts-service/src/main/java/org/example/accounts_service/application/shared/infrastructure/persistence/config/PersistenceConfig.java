package org.example.accounts_service.application.shared.infrastructure.persistence.config;

import org.h2.tools.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class PersistenceConfig {
    
    @SneakyThrows
    @Profile("dev")
    @Bean(initMethod = "start", destroyMethod = "stop")
    public Server inMemoryH2DatabaseServer(
        @Value("${application.h2.tcp-port}") int tcpPort
    )
    {
        log.info("H2 SERVER TCP PORT: {}", tcpPort);
        
        return Server.createTcpServer(
            "-tcp", "-tcpAllowOthers", "-tcpPort", String.valueOf(tcpPort)  
        );
    }
}
