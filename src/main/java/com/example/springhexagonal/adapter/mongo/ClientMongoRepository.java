package com.example.springhexagonal.adapter.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ClientMongoRepository extends MongoRepository<ClientDatabaseModel, String> {
}