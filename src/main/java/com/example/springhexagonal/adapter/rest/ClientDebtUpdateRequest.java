package com.example.springhexagonal.adapter.rest;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

record ClientDebtUpdateRequest(BigDecimal addedAmount) {
    ClientDebtUpdateRequest(@JsonProperty("addedAmount") final BigDecimal addedAmount) {
        this.addedAmount = addedAmount;
    }
}
