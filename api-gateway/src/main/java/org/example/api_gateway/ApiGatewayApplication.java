package org.example.api_gateway;

import org.example.api_gateway.application.security.config.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@SpringBootApplication
@RestController
@Slf4j
@Import({SecurityConfig.class})
public class ApiGatewayApplication {

	public static void main(String[] args) 
	{
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

	@Bean
	public NettyReactiveWebServerFactory reactiveWebServerFactory()
	{
		return new NettyReactiveWebServerFactory();
	}

	@SneakyThrows
	@GetMapping("/token")
	public Mono<Void> getToken(
		@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient authorizedClient,
		@AuthenticationPrincipal OAuth2User userDetails
	)
	{
		log.info(authorizedClient.getAccessToken().getTokenValue());

		return Mono.empty();
	}

}
