package com.example.springhexagonal.adapters.api;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("clients")
public class ClientEndpoint {
    private final ClientFacade clients;

    ClientEndpoint(ClientFacade clients) {
        this.clients = clients;
    }

    @GetMapping("/debt/{client_id}")
    ClientDebtResponse getClientDebt(@PathVariable("client_id") String clientId) {
        return clients.getClientDebt(clientId);
    }

    @PatchMapping("/debt/{client_id}")
    ClientDebtResponse updateClientDebt(@PathVariable("client_id") String clientId,
                                        @RequestBody final ClientDebtUpdateRequest clientDebtUpdateRequest) {
        return clients.addClientDebt(clientId, clientDebtUpdateRequest);
    }

    @PostMapping
    ClientIdResponse createClient(@RequestBody final ClientCreationRequest creationRequest) {
        return clients.create(creationRequest);
    }

}
