package com.example.springhexagonal.adapters.api;

import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
class ClientCreationRequest {
    private final String email;
    private final BigDecimal debt;

    ClientCreationRequest(@JsonProperty("email") final String email, @JsonProperty("debt") final BigDecimal debt) {
        this.email = email;
        this.debt = debt;
    }

    Email email() {
        return Email.of(email);
    }

    Debt debt() {
        return Debt.of(debt);
    }
}
