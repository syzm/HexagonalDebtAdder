package com.example.springhexagonal.adapters.api;

import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Deque;

class ClientDebtResponse {
    private final BigDecimal debt;

    private ClientDebtResponse(final BigDecimal debt) {
        this.debt = debt;
    }

    static ClientDebtResponse of(final Debt debt) {
        return new ClientDebtResponse(debt.getValue());
    }

    @JsonProperty("debt")
    BigDecimal debt() {
        return debt;
    }
}
