package com.example.springhexagonal.adapters.api;

import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

class ClientDebtUpdateRequest {
    private final BigDecimal addedAmount;

    ClientDebtUpdateRequest(@JsonProperty("addedAmount") final BigDecimal addedAmount) {
        this.addedAmount = addedAmount;
    }

    BigDecimal getAddedAmount() {
        return addedAmount;
    }
}
