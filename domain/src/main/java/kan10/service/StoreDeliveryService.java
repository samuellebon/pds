package kan10.service;

import kan10.dao.StoreDeliveryRepository;
import kan10.entities.AbstractDelivery;
import kan10.entities.StoreDelivery;
import kan10.interfaces.IDeliveryService;
import org.springframework.stereotype.Service;

import java.util.List;
/**
 * @author Ilies Faddaoui
 * @version 1.0.0
 */
@Service
public class StoreDeliveryService implements IDeliveryService {

    private StoreDeliveryRepository storeDeliveryRepository;

    public StoreDeliveryService(StoreDeliveryRepository storeDeliveryRepository) {
        this.storeDeliveryRepository = storeDeliveryRepository;
    }

    @Override
    public boolean create(Object delivery) {
        if(delivery != null){
            if(delivery instanceof StoreDelivery) {
                StoreDelivery storeDelivery = (StoreDelivery) delivery;
                storeDeliveryRepository.save(storeDelivery);
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
