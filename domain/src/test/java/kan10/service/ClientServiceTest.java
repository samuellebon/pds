package kan10.service;

import kan10.dao.ClientDao;
import kan10.entities.Client;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ClientServiceTest {

    @Mock
    ClientDao clientDao;

    @InjectMocks
    ClientService clientService;

    @Test
    public void testCreateClient_OK() {

        Client client = new Client(1, "client1@pgm.fr", "000-000-000" );

        List<Client> clientList = spy(new ArrayList<>());
        when(clientDao.findAll()).thenReturn(clientList);

        when(clientDao.save(client)).thenAnswer((i) -> {
            client.setId(1L);
            clientList.add(client);
            return client;
        });

        clientService.createClient(client);
        verify(clientList).add(client);

        List<Client> clients = clientDao.findAll();

        assertEquals(1, clients.size());
        Client actual = clients.get(0);
        assertEquals(new Long(1), actual.getId());
        assertEquals(1, actual.getCode());
        assertEquals("000-000-000", actual.getPhone());
        assertEquals("client1@pgm.fr", actual.getEmail());

    }

    @Test
    public void testGetAllClient_OK() {

        List<Client> clientList = new ArrayList<Client>(){{
            add(new Client(1, "client1@pgm.fr", "000-000-000"));
            add(new Client(2, "client2@pgm.fr", "111-111-111"));
            add(new Client(3, "client3@pgm.fr", "222-222-222"));
            add(new Client(4, "client4@pgm.fr", "333-333-333"));
            add(new Client(5, "client5@pgm.fr", "444-444-444"));
        }};

        when(clientDao.findAll()).thenReturn(clientList);

        List<Client> clients = clientService.getAllClients();

        assertNotNull(clients);
        assertEquals(5, clients.size());

    }

    @Test
    public void testUpdateClient_OK() {

        List<Client> clientList = new ArrayList<Client>(){{
            add(new Client(1, "client1@pgm.fr", "000-000-000" ));
        }};

        when(clientDao.findAll()).thenReturn(clientList);

        Client client = clientService.getAllClients().get(0);

        assertEquals(1, client.getCode());
        assertEquals("000-000-000", client.getPhone());
        assertEquals("client1@pgm.fr", client.getEmail());


        client.setEmail("updated@pgm.fr");
        client.setPhone("111-111-111");

        when(clientDao.save(client)).thenAnswer((i) -> client);

        Client actual = clientService.updateClient(client);

        assertEquals(1, actual.getCode());
        assertEquals("111-111-111", actual.getPhone());
        assertEquals("updated@pgm.fr", actual.getEmail());

    }

}