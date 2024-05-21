package org.example.accounts_service;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
public class AccountsServiceApplication implements CommandLineRunner
{
    public static void main(String[] args) 
    {
        SpringApplication.run(AccountsServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        
        log.info("STARTED !!!");
    }
}
