package com.example.springhexagonal.domain.service;

import com.example.springhexagonal.domain.model.Client;
import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;
import com.example.springhexagonal.domain.ports.in.ClientService;
import com.example.springhexagonal.domain.ports.out.ClientRepository;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;

class ClientServiceTest {

    @Test
    void addDebt_WhenCalled_ShouldUseRepositoryToUpdateClient() {
        ClientRepository mockRepository = Mockito.mock(ClientRepository.class);
        ClientService service = new ClientService(mockRepository);

        ClientId clientId = ClientId.of("some");
        Email email = Email.of("any@example.com");
        Debt initialDebt = Debt.of(BigDecimal.valueOf(400));

        Client client = Client.builder()
                .id(clientId)
                .email(email)
                .debt(initialDebt)
                .build();

        Mockito.when(mockRepository.get(clientId)).thenReturn(client);

        Debt additionalDebt = Debt.of(BigDecimal.valueOf(100));
        service.addDebt(clientId, additionalDebt);

        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        verify(mockRepository).update(clientCaptor.capture());

        Client updatedClient = clientCaptor.getValue();
        assertEquals(0, BigDecimal.valueOf(500).compareTo(updatedClient.getDebt().getValue()),
                "Expected debt did not match the actual updated debt.");
    }

}
