package com.example.springhexagonal.adapters.api;

import com.example.springhexagonal.domain.model.Debt;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ClientEndpoint.class)
class ClientEndpointTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ClientFacade clientFacade;

    @Test
    public void getClientDebt_ShouldReturnDebtInformation() throws Exception {
        BigDecimal expectedDebtValue = new BigDecimal("500.00");
        ClientDebtResponse expectedResponse = ClientDebtResponse.of(Debt.of(expectedDebtValue));
        when(clientFacade.getClientDebt(anyString())).thenReturn(expectedResponse);

        mockMvc.perform(get("/clients/debt/{client_id}", "123"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.debt").value(expectedDebtValue.doubleValue()));
    }

    @Test
    public void createClient_ShouldCreateClientAndReturnClientId() throws Exception {
        ClientCreationRequest creationRequest = new ClientCreationRequest("test@example.com", new BigDecimal("500.00"));

        ObjectMapper objectMapper = new ObjectMapper();
        String creationRequestJson = objectMapper.writeValueAsString(creationRequest);

        ClientIdResponse clientIdResponse = new ClientIdResponse("123");
        when(clientFacade.create(any(ClientCreationRequest.class))).thenReturn(clientIdResponse);

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(creationRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value("123"));
    }

    @Test
    public void updateClientDebt_ShouldUpdateDebtAndReturnUpdatedInformation() throws Exception {
        // Setup
        BigDecimal addedAmount = new BigDecimal("100.00");
        ClientDebtUpdateRequest updateRequest = new ClientDebtUpdateRequest(addedAmount);

        ObjectMapper objectMapper = new ObjectMapper();
        String updateRequestJson = objectMapper.writeValueAsString(updateRequest);

        BigDecimal newTotalDebt = new BigDecimal("600.00");
        ClientDebtResponse updatedResponse = ClientDebtResponse.of(Debt.of(newTotalDebt));
        when(clientFacade.addClientDebt(anyString(), any(ClientDebtUpdateRequest.class))).thenReturn(updatedResponse);

        // Execute & Verify
        mockMvc.perform(patch("/clients/debt/{client_id}", "123")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(updateRequestJson))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.debt").value(newTotalDebt.doubleValue()));
    }
}
