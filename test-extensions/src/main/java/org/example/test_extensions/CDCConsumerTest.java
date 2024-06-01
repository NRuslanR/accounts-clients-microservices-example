package org.example.test_extensions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.context.annotation.Import;

@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Target(ElementType.TYPE)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
@Import(ConsumerDrivenContractTestConfig.class)
@EnabledIfSystemProperty(named = "spring.profiles.active", matches = "(.*)cdc(.*)")
public @interface CDCConsumerTest 
{
    
}
