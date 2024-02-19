package com.example.springhexagonal.adapters.clientdb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientMongoRepository extends MongoRepository<ClientDatabaseModel, String> {
}