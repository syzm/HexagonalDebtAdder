package com.example.springhexagonal.domain.service;

import com.example.springhexagonal.domain.model.Client;
import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;
import com.example.springhexagonal.port.ClientRepository;
import com.example.springhexagonal.port.ClientService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientServiceImplementationTest {

    @Mock
    private ClientRepository clientRepository;

    private ClientService clientService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        clientService = new ClientServiceImplementation(clientRepository);
    }

    @Test
    void createClient_ShouldCreateClientSuccessfully() {
        // Given
        Email email = Email.of("test@example.com");
        Debt initialDebt = Debt.of(BigDecimal.valueOf(1000));
        ClientId expectedClientId = ClientId.of("1");
        Client mockClient = new Client(expectedClientId, email, initialDebt);
        when(clientRepository.create(email, initialDebt)).thenReturn(mockClient);

        // When
        ClientId actualClientId = clientService.createClient(email, initialDebt);

        // Then
        assertEquals(expectedClientId, actualClientId);
        verify(clientRepository).create(email, initialDebt);
    }


    @Test
    void addDebt_ShouldCorrectlyAddDebtToClient() {
        // Given
        ClientId clientId = ClientId.of("1");
        Debt additionalDebt = Debt.of(BigDecimal.valueOf(500));
        Debt expectedDebt = Debt.of(BigDecimal.valueOf(1500));
        Client clientBeforeUpdate = new Client(clientId, Email.of("test@example.com"), Debt.of(BigDecimal.valueOf(1000)));
        Client clientAfterUpdate = new Client(clientId, Email.of("test@example.com"), expectedDebt);

        when(clientRepository.get(clientId)).thenReturn(clientBeforeUpdate);
        when(clientRepository.update(any(Client.class))).thenReturn(clientAfterUpdate);

        // When
        Debt resultDebt = clientService.addDebt(clientId, additionalDebt);

        // Then
        assertEquals(expectedDebt, resultDebt);
        verify(clientRepository).get(clientId);
        verify(clientRepository).update(clientBeforeUpdate);
    }

    @Test
    void getClientDebt_ShouldReturnCorrectDebt() {
        // Given
        ClientId clientId = ClientId.of("1");
        Debt expectedDebt = Debt.of(BigDecimal.valueOf(1000));
        Client mockClient = new Client(clientId, Email.of("test@example.com"), expectedDebt);

        when(clientRepository.get(clientId)).thenReturn(mockClient);

        // When
        Debt actualDebt = clientService.getClientDebt(clientId);

        // Then
        assertEquals(expectedDebt, actualDebt);
        verify(clientRepository).get(clientId);
    }

}
