package org.example.accounts_service.application.accounts.api;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.example.accounts_service.application.accounts.features.AccountDto;
import org.example.accounts_service.application.accounts.features.creating.CreateAccountCommand;
import org.example.accounts_service.application.accounts.features.deposit.DepositAccountCommand;
import org.example.accounts_service.application.accounts.features.shared.config.UnitAccountsApiTest;
import org.example.accounts_service.application.accounts.features.withdrawal.WithdrawAccountCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import lombok.SneakyThrows;

@UnitAccountsApiTest
@WebMvcTest(controllers = HttpAccountsApi.class)
public class UnitHttpAccountsApiTests extends HttpAccountsApiTests
{
    @Autowired
    private MockMvc mockMvc;

    @SneakyThrows
    @Override
    protected Object executeCreateAccountRequest(String createAccountRequestBody) {
        
        return 
            mockMvc
                .perform(
                    post("/api/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(createAccountRequestBody)
                        .accept(MediaType.APPLICATION_JSON)
                );
    }

    @SneakyThrows
    @Override
    protected void assertCreateAccountResponse(Object response, CreateAccountCommand command) {
        
        ((ResultActions)response)
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(header().exists(HttpHeaders.LOCATION))
            .andExpect(jsonPath("$.account").isNotEmpty())
            .andExpect(jsonPath("$.account.id").isString())
            .andExpect(jsonPath("$.account.name").value(command.getName()))
            .andExpect(jsonPath("$.account.amount").value(command.getAmount()));
    }

    @SneakyThrows
    @Override
    protected Object executeDepositAccountRequest(
        String depositAccountRequestBody, DepositAccountCommand command
    ) 
    {  
        return 
            mockMvc
                .perform(
                    post("/api/accounts/{id}/deposit", command.getAccountId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(depositAccountRequestBody)
                    .accept(MediaType.APPLICATION_JSON)
                );
    }

    @SneakyThrows
    @Override
    protected void assertDepositAccountResponse(Object response, AccountDto account, DepositAccountCommand command) {
        
        ((ResultActions)response)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.account").isNotEmpty())
            .andExpect(jsonPath("$.account.amount").value(account.getAmount() + command.getDepositAmount()));
    }

    @SneakyThrows
    @Override
    protected String extractResponseBody(Object response) 
    {    
        return 
            new ObjectMapper().writeValueAsString(
                JsonPath.parse(
                    ((ResultActions)response)
                        .andReturn()
                        .getResponse()
                        .getContentAsString()
                ).read("account")
            );
    }

    @SneakyThrows
    @Override
    protected void assertDepositAccountNotFound(
        Object response, 
        DepositAccountCommand command
    ) 
    {
        ((ResultActions)response)
            .andExpect(status().isNotFound())
            .andExpect(jsonPath("$.error.message").isNotEmpty());
    }

    @SneakyThrows
    @Override
    protected Object executeWithdrawAccountRequest(
        String withdrawAccountRequestBody, WithdrawAccountCommand command) 
    {
        return
            mockMvc
                .perform(
                    post("/api/accounts/{id}/withdraw", command.getWithdrawAmount())
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .content(withdrawAccountRequestBody)
                );
    }

    @SneakyThrows
    @Override
    protected void assertWithdrawAccountResponse(Object response, AccountDto account, WithdrawAccountCommand command) {
        
        ((ResultActions)response)
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.account.amount").value(account.getAmount() - command.getWithdrawAmount()));
    }

    @SneakyThrows
    @Override
    protected void assertBadWithdrawAccountResponse(Object response, WithdrawAccountCommand invalidCommand) {
        
        ((ResultActions)response)
            .andExpect(status().isBadRequest())
            .andExpect(jsonPath("$.error.message").isNotEmpty());
    }
}
