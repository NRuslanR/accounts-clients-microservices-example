package org.example.accounts_view_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;

/*
 * To-dos:
 * 1. Consider transactionality with reactive mongodb
 * 2. Race condition for read-write ops because the saving of single document is considered as atomic
 * 3. Consider the necessity of spring-retry to retry update ops due to optimistic locking for duplicate event messages
 * 4. Add mongodb testcontainer
 */
@SpringBootApplication
public class AccountsViewServiceApplication 
{
    public static void main(String[] args) 
    {
        SpringApplication.run(AccountsViewServiceApplication.class, args);
    }

    @Bean
    public NettyReactiveWebServerFactory reactiveWebServerFactory()
    {
        return new NettyReactiveWebServerFactory();
    }
}
