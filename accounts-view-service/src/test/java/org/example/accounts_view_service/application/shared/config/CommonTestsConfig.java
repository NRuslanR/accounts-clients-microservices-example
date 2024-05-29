package org.example.accounts_view_service.application.shared.config;

import org.example.accounts_view_service.application.shared.data.generating.TestCreateAccountViewImpl;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Import;

@TestConfiguration
@Import(TestCreateAccountViewImpl.class)
public class CommonTestsConfig {
    
}
