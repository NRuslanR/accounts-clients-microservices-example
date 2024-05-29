package org.example.accounts_service.application.accounts.api;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.emptyOrNullString;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.oneOf;

import org.example.accounts_service.application.accounts.features.AccountDto;
import org.example.accounts_service.application.accounts.features.creating.CreateAccountCommand;
import org.example.accounts_service.application.accounts.features.deposit.DepositAccountCommand;
import org.example.accounts_service.application.accounts.features.withdrawal.WithdrawAccountCommand;
import org.example.test_extensions.E2EApiTest;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import lombok.SneakyThrows;

@E2EApiTest
public class E2EHttpAccountsApiTests extends HttpAccountsApiTests
{
    private final String baseServerUrl;

    public E2EHttpAccountsApiTests(
        @Value("http://localhost:${local.server.port}") String baseServerUrl
    )
    {
        this.baseServerUrl = baseServerUrl;
    }

    @Override
    protected Object executeCreateAccountRequest(String createAccountRequestBody) {
        
        return
            RestAssured
                .given()
                    .baseUri(baseServerUrl)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(createAccountRequestBody)
                .when()
                    .post("/api/accounts")
                    .andReturn();
    }

    @Override
    protected void assertCreateAccountResponse(Object response, CreateAccountCommand command) {
        
        Response httpResponse = (Response)response;

        assertThat(httpResponse.getStatusCode(), is(equalTo(HttpStatus.CREATED.value())));
        assertThat(httpResponse.getContentType(), is(oneOf(ContentType.JSON.getContentTypeStrings())));

        var bodyJson = httpResponse.getBody().jsonPath();

        assertThat(bodyJson.getString("account"), is(not(emptyOrNullString())));
        assertThat(bodyJson.getString("account.id"), is(not(emptyOrNullString())));
        assertThat(bodyJson.getString("account.name"), is(equalTo(command.getName())));
        assertThat(bodyJson.getInt("account.amount"), is(equalTo(command.getAmount())));
    }

    @Override
    protected Object executeDepositAccountRequest(
        String depositAccountRequestBody, DepositAccountCommand command) 
    {
        return
            RestAssured
                .given()
                    .baseUri(baseServerUrl)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(depositAccountRequestBody)
                .when()
                    .post("/api/accounts/{id}/deposit", command.getAccountId());
    }

    @Override
    protected void assertDepositAccountResponse(
        Object response, 
        AccountDto account, 
        DepositAccountCommand command
    ) 
    {
       var httpResponse = (Response)response;
        
        assertThat(httpResponse.getStatusCode(), is(equalTo(HttpStatus.OK.value())));
        assertThat(httpResponse.getContentType(), is(oneOf(ContentType.JSON.getContentTypeStrings())));

        var bodyJsonPath = httpResponse.getBody().jsonPath();

        assertThat(bodyJsonPath.getString("account"), is(not(emptyOrNullString())));
        assertThat(bodyJsonPath.getInt("account.amount"), is(equalTo(account.getAmount() + command.getDepositAmount())));
    }

    @SneakyThrows
    @Override
    protected String extractResponseBody(Object response) 
    {     
        return 
            new ObjectMapper().writeValueAsString(
                JsonPath.parse(
                    ((Response)response).getBody().asString()
                ).read("account")
            );
    }

    @Override
    protected void assertDepositAccountNotFound(Object response, DepositAccountCommand command) {
        
        var httpResponse = (Response)response;

        assertThat(httpResponse.getStatusCode(), is(equalTo(HttpStatus.NOT_FOUND.value())));
        assertThat(httpResponse.getBody().jsonPath().getString("error.message"), is(not(emptyOrNullString()))); 
    }

    @Override
    protected Object executeWithdrawAccountRequest(
        String withdrawAccountRequestBody, 
        WithdrawAccountCommand command) 
    {
        return
            RestAssured
                .given()
                    .baseUri(baseServerUrl)
                    .contentType(ContentType.JSON)
                    .accept(ContentType.JSON)
                    .body(withdrawAccountRequestBody)
                .when()
                    .post("/api/accounts/{id}/withdraw", command.getAccountId());
    }

    @Override
    protected void assertWithdrawAccountResponse(
        Object response, 
        AccountDto account, 
        WithdrawAccountCommand command
    ) 
    {
        var httpResponse = (Response)response;

        assertThat(httpResponse.getStatusCode(), is(equalTo(HttpStatus.OK.value())));
        assertThat(httpResponse.jsonPath().getInt("account.amount"), is(equalTo(account.getAmount() - command.getWithdrawAmount())));
    }

    @Override
    protected void assertBadWithdrawAccountResponse(Object response, WithdrawAccountCommand invalidCommand) {
        
        var httpResponse = (Response)response;

        assertThat(httpResponse.getStatusCode(), is(equalTo(HttpStatus.BAD_REQUEST.value())));
        assertThat(httpResponse.jsonPath().getString("error.message"), is(not(emptyOrNullString())));   
    }
}
