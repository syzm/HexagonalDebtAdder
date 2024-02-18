package com.example.springhexagonal.domain.model;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    // Used value objects: https://martinfowler.com/bliki/ValueObject.html
    private ClientId id;
    private Email email;
    private Money money;

    public void incrementMoney(double amount) {
        double newValue = this.money.value() + amount;
        this.money = Money.of(newValue);
    }

    public void decrementMoney(double amount) {
        double newValue = this.money.value() - amount;
        if (newValue < 0) {
            throw new IllegalArgumentException("Money cannot be negative");
        }
        this.money = Money.of(newValue);
    }
}
