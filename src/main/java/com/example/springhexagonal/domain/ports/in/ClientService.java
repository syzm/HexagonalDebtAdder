package com.example.springhexagonal.domain.ports.in;

import com.example.springhexagonal.domain.model.Client;
import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;
import com.example.springhexagonal.domain.ports.out.ClientRepository;

public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(final ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientId createClient(Email email, Debt initialDebt) {
        final Client client = clientRepository.create(email, initialDebt);
        return client.getId();
    }

    public Debt addDebt(ClientId clientId, Debt amount) {
        Client client = clientRepository.get(clientId);
        client.addDebt(amount.getValue());
        clientRepository.update(client);
        return client.getDebt();
    }

    public Debt getClientDebt(ClientId clientId) {
        Client client = clientRepository.get(clientId);
        return client.getDebt();
    }
}
