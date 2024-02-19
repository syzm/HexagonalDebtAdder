package com.example.springhexagonal.domain.ports.in;

import com.example.springhexagonal.domain.model.Client;
import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;
import com.example.springhexagonal.domain.ports.out.ClientRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientId createOrUpdateClient(Email email, Debt initialDebt) {
        final Client client = clientRepository.create(email, initialDebt);
        return client.getId();
    }

    public void addDebt(ClientId clientId, Debt amount) {
        Client client = clientRepository.get(clientId);
        client.addDebt(amount.getValue());
        clientRepository.update(client);
    }

    public Debt getClientDebt(ClientId clientId) {
        Client client = clientRepository.get(clientId);
        return client.getDebt();
    }
}
