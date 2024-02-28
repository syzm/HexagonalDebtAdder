package com.example.springhexagonal.domain.service;

import com.example.springhexagonal.domain.model.Client;
import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;
import com.example.springhexagonal.port.ClientRepository;
import com.example.springhexagonal.port.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
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

    public Debt addDebt(ClientId clientId, Debt amount) {
        Client client = clientRepository.get(clientId);
        client.addDebt(amount.getValue());
        Client updatedClient = clientRepository.update(client);
        return updatedClient.getDebt();
    }

    @Override
    public Debt getClientDebt(ClientId clientId) {
        Client client = clientRepository.get(clientId);
        return client.getDebt();
    }
}
