package com.example.springhexagonal.domain.ports;

import com.example.springhexagonal.domain.model.Client;
import com.example.springhexagonal.domain.model.Email;
import com.example.springhexagonal.domain.model.Money;

import java.util.Optional;

public interface ClientRepository {

    Client save(Email email, Money money);
    Optional<Client> findById(String clientId);
}
