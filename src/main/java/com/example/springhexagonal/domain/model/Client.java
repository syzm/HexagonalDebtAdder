package com.example.springhexagonal.domain.model;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    // Used value objects: https://martinfowler.com/bliki/ValueObject.html
    private ClientId id;
    private Email email;
    private Debt debt;

    public void addDebt(BigDecimal amount) {
        this.debt = this.debt.add(amount);
    }
}
