package org.example.accounts_view_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;

/*
 * To-dos:
 * 1. Add tests for changing state of the account views to check retries 
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
