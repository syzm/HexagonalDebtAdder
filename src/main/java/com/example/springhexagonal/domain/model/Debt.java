package com.example.springhexagonal.domain.model;

import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class Debt {
    private final BigDecimal value;

    private Debt(final BigDecimal value) {
        this.value = value;
    }

    public static Debt of(final BigDecimal debt) {
        return new Debt(debt);
    }

    public Debt add(final BigDecimal amount) {
        return new Debt(this.value.add(amount));
    }
}