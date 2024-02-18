package com.example.springhexagonal.domain.model;

public class Email {
    private final String value;

    private Email(final String value) {
        this.value = value;
    }

    public static Email of(final String email) {
        return new Email(email);
    }

    public String value() {
        return value;
    }
}