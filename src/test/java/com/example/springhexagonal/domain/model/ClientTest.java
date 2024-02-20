package com.example.springhexagonal.domain.model;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ClientTest {

    @Test
    void addDebt_ShouldCorrectlyIncrementDebtValue() {
        Client client = new Client(ClientId.of("some-id"), Email.of("test@example.com"),
                Debt.of(BigDecimal.valueOf(100)));

        client.addDebt(BigDecimal.valueOf(50));

        assertEquals(0, BigDecimal.valueOf(150).compareTo(client.getDebt().getValue()));
    }
}
