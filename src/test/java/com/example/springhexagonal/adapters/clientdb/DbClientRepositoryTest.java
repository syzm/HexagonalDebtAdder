package com.example.springhexagonal.adapters.clientdb;

import com.example.springhexagonal.domain.model.Client;
import com.example.springhexagonal.domain.model.ClientId;
import com.example.springhexagonal.domain.model.Debt;
import com.example.springhexagonal.domain.model.Email;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.math.BigDecimal;
import java.util.Optional;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class DbClientRepositoryTest {

    @Mock
    private ClientMongoRepository repository;

    @InjectMocks
    private DbClientRepository dbClientRepository;

    @Captor
    private ArgumentCaptor<ClientDatabaseModel> modelCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void get_ShouldReturnClientWhenFound() {
        String clientId = "1";
        ClientDatabaseModel model = ClientDatabaseModel.builder()
                .id(clientId)
                .email("test@example.com")
                .debt(new BigDecimal("100"))
                .build();
        when(repository.findById(clientId)).thenReturn(Optional.of(model));

        Client result = dbClientRepository.get(ClientId.of(clientId));

        assertNotNull(result);
        assertEquals(clientId, result.getId().getValue());
        assertEquals("test@example.com", result.getEmail().getValue());
        assertEquals(0, BigDecimal.valueOf(100).compareTo(result.getDebt().getValue()));
    }

    @Test
    void create_ShouldPersistClientAndReturnIt() {
        Email email = Email.of("new@example.com");
        Debt debt = Debt.of(new BigDecimal("200"));
        ClientDatabaseModel savedModel = ClientDatabaseModel.builder()
                .id("1")
                .email(email.getValue())
                .debt(debt.getValue())
                .build();
        when(repository.save(any(ClientDatabaseModel.class))).thenReturn(savedModel);

        Client result = dbClientRepository.create(email, debt);

        assertNotNull(result);
        assertEquals("1", result.getId().getValue());
        assertEquals(email.getValue(), result.getEmail().getValue());
        assertEquals(0, debt.getValue().compareTo(result.getDebt().getValue()));
    }


    @Test
    void update_ShouldUpdateClient() {
        Client clientToUpdate = new Client(ClientId.of("1"), Email.of("update@example.com"), Debt.of(new BigDecimal("300")));

        dbClientRepository.update(clientToUpdate);

        verify(repository).save(modelCaptor.capture());
        ClientDatabaseModel capturedModel = modelCaptor.getValue();
        assertEquals("1", capturedModel.getId());
        assertEquals("update@example.com", capturedModel.getEmail());
        assertEquals(new BigDecimal("300"), capturedModel.getDebt());
    }
}
