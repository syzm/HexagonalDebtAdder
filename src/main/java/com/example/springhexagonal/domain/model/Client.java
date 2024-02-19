package com.example.springhexagonal.domain.model;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Client {
    // Used value objects: https://martinfowler.com/bliki/ValueObject.html
    private ClientId id;
    private Email email;
    private Money money;


}
