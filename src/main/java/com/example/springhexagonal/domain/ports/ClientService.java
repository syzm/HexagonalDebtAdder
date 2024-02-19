package com.example.springhexagonal.domain.ports;

import com.example.springhexagonal.domain.model.Client;
import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Email;
import com.example.springhexagonal.domain.model.Money;
import com.example.springhexagonal.exceptions.ClientNotFoundException;

public final class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientId create(final Email email, final Money money) {
        final Client client = clientRepository.save(email, money);
        return client.getId();
    }

    public Client findById(String id) {
        return clientRepository.findById(id)
                .orElseThrow(() -> new ClientNotFoundException("Client not found"));
    }

}
