package com.example.springhexagonal.domain.service;

import com.example.springhexagonal.domain.model.Client;
import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;
import com.example.springhexagonal.application.ClientRepository;
import com.example.springhexagonal.application.ClientService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImplementation implements ClientService {
    private final ClientRepository clientRepository;

    public ClientServiceImplementation(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    @Override
    public ClientId createClient(Email email, Debt initialDebt) {
        final Client client = clientRepository.create(email, initialDebt);
        return client.getId();
    }

    @Override
    public Debt addDebt(ClientId clientId, Debt amount) {
        Client client = clientRepository.get(clientId);
        client.addDebt(amount.getValue());
        clientRepository.update(client);
        return client.getDebt();
    }

    @Override
    public Debt getClientDebt(ClientId clientId) {
        Client client = clientRepository.get(clientId);
        return client.getDebt();
    }
}
