package com.example.springhexagonal.domain.model;

import lombok.Getter;

@Getter
public class Money {
    private final double value;

    private Money(final double value) {
        this.value = value;
    }

    public static Money of(final double money) {
        return new Money(money);
    }
}