package kan10.service;

import kan10.dao.BoxDeliveryRepository;
import kan10.entities.AbstractDelivery;
import kan10.entities.BoxDelivery;
import kan10.interfaces.IDeliveryService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author Ilies Faddaoui
 * @version 1.0.0
 */
@Service
public class BoxDeliveryService implements IDeliveryService {

    private BoxDeliveryRepository boxDeliveryRepository;

    public BoxDeliveryService(BoxDeliveryRepository boxDeliveryRepository) {
        this.boxDeliveryRepository = boxDeliveryRepository;
    }

    @Override
    public boolean create(Object delivery) {
        if(delivery != null){
            if(delivery instanceof BoxDelivery) {
                BoxDelivery boxDelivery = (BoxDelivery) delivery;
                boxDeliveryRepository.save(boxDelivery);
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
