package com.example.springhexagonal.adapter.mongo;

import com.example.springhexagonal.port.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.utility.DockerImageName;

@DataMongoTest
@Import({DbClientRepository.class})
public class DbClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    static final MongoDBContainer mongoDBContainer;

    static {
        mongoDBContainer = new MongoDBContainer(DockerImageName.parse("mongo:4.4.6"));
        mongoDBContainer.start();
    }


}
