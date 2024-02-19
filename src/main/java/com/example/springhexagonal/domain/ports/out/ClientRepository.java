package com.example.springhexagonal.domain.ports.out;

import com.example.springhexagonal.domain.model.Client;
import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;

import java.util.Optional;

public interface ClientRepository {
    Client get(ClientId clientId);
    Client create(Email email, Debt debt);
    Client update(Client client);
}
