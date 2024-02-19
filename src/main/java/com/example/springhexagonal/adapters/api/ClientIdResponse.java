package com.example.springhexagonal.adapters.api;

import com.example.springhexagonal.domain.model.ClientId;
import com.fasterxml.jackson.annotation.JsonProperty;

class ClientIdResponse {

    private final String id;

    private ClientIdResponse(final String id) {
        this.id = id;
    }

    static ClientIdResponse of(final ClientId clientId) {
        return new ClientIdResponse(clientId.getValue());
    }

    @JsonProperty("id")
    String getId() {
        return id;
    }
}
