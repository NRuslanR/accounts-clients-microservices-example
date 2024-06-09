package org.example.clients_service.application.features.create_client;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;

import org.example.clients_service.application.shared.config.ClientsUnitApiTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.SneakyThrows;

@ClientsUnitApiTest
@WebMvcTest(controllers = HttpCreateClientEndpoint.class)
public class UnitHttpCreateClientEndpointTests 
{
    @MockBean
    private CreateClient createClient;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @SneakyThrows
    @WithMockUser(authorities = {
        "CLIENT_RESERVATION"
    })
    @Test
    public void should_Return_ValidResponse_When_RequestValid()
    {
        var command = 
            CreateClientCommand.of(
                "#1", 
                LocalDateTime.now().plusMonths(1)
            );
   
        mockMvc
            .perform(
                post("/api/clients")
                    .with(SecurityMockMvcRequestPostProcessors.csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(command)) 
                    .accept(MediaType.APPLICATION_JSON) 
            )
            .andExpect(status().isCreated())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(jsonPath("$.client").isNotEmpty())
            .andExpect(jsonPath("$.client.id").isNotEmpty())
            .andExpect(jsonPath("$.client.name").value(command.getName()))
            .andExpect(jsonPath("$.client.reservationExpiredAt").value(command.getReservationExpiredAt()));
    }
}
