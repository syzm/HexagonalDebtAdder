package com.example.springhexagonal.domain.ports.out;

import com.example.springhexagonal.domain.model.Client;
import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;

public interface ClientRepository {
    Client get(ClientId clientId);
    Client create(Email email, Debt debt);
    void update(Client client);
}
