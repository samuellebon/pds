package kan10.interfaces;

import kan10.entities.Client;

import java.util.List;

public interface IClientService {

    Client createClient (Client client);
    List<Client> getAllClients();
    Client updateClient (Client client);

}
