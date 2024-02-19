package com.example.springhexagonal.domain.model;


import lombok.Getter;

@Getter
public class ClientId {
    private final String value;

    private ClientId(final String value) {
        this.value = value;
    }

    public static ClientId of(final String clientId) {
        return new ClientId(clientId);
    }
}
