package com.example.springhexagonal.adapters.clientdb;

import com.example.springhexagonal.domain.model.Client;
import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientDatabaseModelTest {

    @Test
    void modelConversion_ShouldCorrectlyConvertToAndFromDomainModel() {
        Client originalClient = Client.builder()
                .id(ClientId.of("1"))
                .email(Email.of("test@example.com"))
                .debt(Debt.of(new BigDecimal("100")))
                .build();

        ClientDatabaseModel model = ClientDatabaseModel.of(originalClient);
        assertEquals(originalClient.getId().getValue(), model.getId());
        assertEquals(originalClient.getEmail().getValue(), model.getEmail());
        assertEquals(0, originalClient.getDebt().getValue().compareTo(model.getDebt()));

        Client convertedClient = model.toDomain();
        assertEquals(originalClient.getId().getValue(), convertedClient.getId().getValue());
        assertEquals(originalClient.getEmail().getValue(), convertedClient.getEmail().getValue());
        assertEquals(0, originalClient.getDebt().getValue().compareTo(convertedClient.getDebt().getValue()));
    }
}
