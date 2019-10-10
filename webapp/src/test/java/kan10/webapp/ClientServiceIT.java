package kan10.webapp;

import kan10.entities.Client;
import kan10.service.*;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ClientServiceIT extends AbstractIT {

    @Inject
    ClientService clientService;

    @Before
    public void init() {
        Client client = new Client();
        client.setCode(1);
        client.setPhone("000-000-000");
        client.setEmail("client1@pgm.fr");
        clientService.createClient(client);
    }

    @Test
    public void testCreateClient_OK() {

        Client client = new Client();
        client.setCode(2);
        client.setPhone("000-000-000");
        client.setEmail("client2@pgm.fr");
        clientService.createClient(client);

        List<Client> clients = clientService.getAllClients();

        assertEquals(2, clients.size());
        Client actual = clients.get(1);
        assertEquals(2, actual.getCode());
        assertEquals("000-000-000", actual.getPhone());
        assertEquals("client2@pgm.fr", actual.getEmail());

    }

    @Test
    public void testFindAllClient_OK() {
        List<Client> clients = clientService.getAllClients();
        assertEquals(1, clients.size());
        Client actual = clients.get(0);
        assertEquals(1, actual.getCode());
        assertEquals("000-000-000", actual.getPhone());
        assertEquals("client1@pgm.fr", actual.getEmail());
    }

    @Test
    public void testUpdateClient_OK() {

        Client client = clientService.getAllClients().get(0);
        client.setEmail("actual@pgm.fr");
        client.setPhone("111-111-111");

        clientService.updateClient(client);

        Client actual = clientService.getAllClients().get(0);

        assertEquals(1, actual.getCode());
        assertEquals("111-111-111", actual.getPhone());
        assertEquals("actual@pgm.fr", actual.getEmail());

    }

}