package com.example.springhexagonal.application;

import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;

public interface ClientService {
    ClientId createClient(Email email, Debt initialDebt);
    Debt addDebt(ClientId clientId, Debt amount);
    public Debt getClientDebt(ClientId clientId);
}
