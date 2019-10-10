package kan10.service;

import kan10.dao.HomeDeliveryRepository;
import kan10.entities.AbstractDelivery;
import kan10.entities.HomeDelivery;
import kan10.interfaces.IDeliveryService;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Ilies Faddaoui
 * @version 1.0.0
 */
@Service
public class HomedeliveryService implements IDeliveryService {
    private HomeDeliveryRepository homeDeliveryRepository;

    public HomedeliveryService(HomeDeliveryRepository homeDeliveryRepository) {
        this.homeDeliveryRepository = homeDeliveryRepository;
    }

    @Override
    public boolean create(Object delivery) {
        if(delivery != null){
            if(delivery instanceof HomeDelivery) {
                HomeDelivery homeDelivery = (HomeDelivery) delivery;
                homeDeliveryRepository.save(homeDelivery);
                return true;
            }
        }
        return false;

    }

    @Override
    public List<Object> read() {
        return null;
    }

    @Override
    public void update(AbstractDelivery delivery) {

    }

    @Override
    public void delete(String id) {

    }

    @Override
    public AbstractDelivery search(String id) {
        return null;
    }
}
