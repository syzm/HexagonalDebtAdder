package com.example.springhexagonal.config;

import com.example.springhexagonal.domain.ports.in.ClientService;
import com.example.springhexagonal.domain.ports.out.ClientRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ClientConfig {

    @Bean
    ClientService clientService(final ClientRepository clientRepository) {
        return new ClientService(clientRepository);
    }
}
