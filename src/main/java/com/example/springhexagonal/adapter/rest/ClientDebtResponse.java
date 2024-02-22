package com.example.springhexagonal.adapter.rest;

import com.example.springhexagonal.domain.model.Debt;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

record ClientDebtResponse(BigDecimal debt) {

    static ClientDebtResponse of(final Debt debt) {
        return new ClientDebtResponse(debt.getValue());
    }

    @Override
    @JsonProperty("debt")
    public BigDecimal debt() {
        return debt;
    }
}
