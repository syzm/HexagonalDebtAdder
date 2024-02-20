package com.example.springhexagonal.adapters.api;

import com.example.springhexagonal.domain.model.ClientId;
import com.fasterxml.jackson.annotation.JsonProperty;

record ClientIdResponse(String id) {

    static ClientIdResponse of(final ClientId clientId) {
        return new ClientIdResponse(clientId.getValue());
    }

    @Override
    @JsonProperty("id")
    public String id() {
        return id;
    }
}
