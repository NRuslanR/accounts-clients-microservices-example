package org.example.accounts_service.application.accounts.api;

import java.util.stream.Stream;

import org.example.accounts_service.application.accounts.features.AccountDto;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;

@ControllerAdvice
public class HttpApiCreatedResourceLocationAdvice implements ResponseBodyAdvice<Object> 
{
    @Override
    public boolean supports(
        MethodParameter returnType, 
        Class<? extends HttpMessageConverter<?>> converterType
    ) 
    {
        return true;
    }
    
    @SneakyThrows
    @Override
    public Object beforeBodyWrite(
        Object body, 
        MethodParameter returnType, 
        MediaType selectedContentType,
        Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request,
        ServerHttpResponse response
    ) 
    {
        HttpServletResponse servletResponse = 
            ((ServletServerHttpResponse)response).getServletResponse();

        if (
            servletResponse.getStatus() != HttpStatus.CREATED.value() ||
            !Stream
                .of(body.getClass().getMethods())
                .anyMatch(m -> m.getName().equals("getAccount"))
        )
        {
            return body;
        }

        var account = (AccountDto)body.getClass().getMethod("getAccount").invoke(body);
        
        var location =
            ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .replacePath(null)
                .path("/api/accounts/{id}")
                .buildAndExpand(account.getId())
                .toUriString();

        response.getHeaders().add(HttpHeaders.LOCATION, location); 

        return body;
    } 
}
