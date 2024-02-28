package com.example.springhexagonal.port;

import com.example.springhexagonal.domain.model.Client;
import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;

public interface ClientRepository {
    Client get(ClientId clientId);
    Client create(Email email, Debt debt);
    Client update(Client client);
}
