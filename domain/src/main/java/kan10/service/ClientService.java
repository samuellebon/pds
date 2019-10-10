package kan10.service;

import kan10.dao.ClientDao;
import kan10.entities.Client;
import kan10.interfaces.IClientService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements IClientService {

    private final ClientDao clientDao;

    @Inject
    public ClientService(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    @Override
    public Client createClient (Client client) {
        return clientDao.save(client);
    }

    @Override
    public Client updateClient (Client client) {
        return clientDao.save(client);
    }

    @Override
    public List<Client> getAllClients() {
        return clientDao.findAll();
    }


    public Optional<Client> findClientById (Long id) {return clientDao.findById(id);}

}
