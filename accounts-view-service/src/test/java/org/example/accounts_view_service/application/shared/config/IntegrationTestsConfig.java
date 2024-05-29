package org.example.accounts_view_service.application.shared.config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@TestConfiguration
@Import(CommonTestsConfig.class)
public class IntegrationTestsConfig 
{
    private static MongoDBContainer mongoDBContainer = 
        new MongoDBContainer(
            DockerImageName.parse("mongo:4.0.10")
        ).withReuse(true);

    static
    {
        mongoDBContainer.start();

        System.setProperty("spring.data.mongodb.uri", mongoDBContainer.getReplicaSetUrl());
    }
}
