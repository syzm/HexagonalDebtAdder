package com.example.springhexagonal.adapter.mongo;

import com.example.springhexagonal.domain.model.Client;
import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;
import com.example.springhexagonal.application.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
class DbClientRepository implements ClientRepository {
    private final ClientMongoRepository repository;


    @Override
    public Client get(ClientId clientId) {
        Optional<ClientDatabaseModel> model = repository.findById(clientId.getValue());
        return model.map(ClientDatabaseModel::toDomain).orElse(null);
    }

    @Override
    public Client create(Email email, Debt debt) {
        ClientDatabaseModel model = ClientDatabaseModel.builder().email(email.getValue()).debt(debt.getValue()).build();
        ClientDatabaseModel savedModel = repository.save(model);
        return savedModel.toDomain();
    }

    @Override
    public void update(Client client) {
        ClientDatabaseModel model = ClientDatabaseModel.of(client);
        repository.save(model);
    }
}
