package com.example.springhexagonal.adapter.mongo;
import com.example.springhexagonal.domain.model.Client;
import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.mongodb.core.mapping.Document;
import java.math.BigDecimal;

@Document
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
@Getter
@TypeAlias("client")
class ClientDatabaseModel {
    @Id
    private String id;
    private String email;
    private BigDecimal debt;

    static ClientDatabaseModel of(Client client) {
        return ClientDatabaseModel.builder()
                .id(client.getId().getValue())
                .email(client.getEmail().getValue())
                .debt(client.getDebt().getValue())
                .build();
    }

    public Client toDomain() {
        return Client.builder()
                .id(ClientId.of(id))
                .email(Email.of(email))
                .debt(Debt.of(debt))
                .build();
    }
}
