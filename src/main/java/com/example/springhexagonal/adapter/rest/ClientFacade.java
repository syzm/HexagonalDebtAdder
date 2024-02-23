package com.example.springhexagonal.adapter.rest;

import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.application.ClientService;
import org.springframework.stereotype.Component;

@Component
class ClientFacade {
    private final ClientService clientService;

    ClientFacade(final ClientService clientService) {
        this.clientService = clientService;
    }

    ClientIdResponse create(final ClientCreationRequest creationRequest) {
        final ClientId clientId = clientService.createClient(creationRequest.email(), creationRequest.debt());
        return ClientIdResponse.of(clientId);
    }

    ClientDebtResponse getClientDebt(final String clientId) {
        final Debt debt = clientService.getClientDebt(ClientId.of(clientId));
        return ClientDebtResponse.of(debt);
    }

    ClientDebtResponse addClientDebt(final String clientId, final ClientDebtUpdateRequest clientDebtUpdateRequest) {
        final Debt debt = clientService.addDebt(ClientId.of(clientId), Debt.of(clientDebtUpdateRequest.addedAmount()));
        return ClientDebtResponse.of(debt);
    }
}
