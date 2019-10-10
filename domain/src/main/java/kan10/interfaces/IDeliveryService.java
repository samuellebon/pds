package kan10.interfaces;

import kan10.entities.AbstractDelivery;

import java.util.List;

/**
 * @author Ilies Faddaoui
 * @version 1.0.0
 */
public interface IDeliveryService {
    public boolean create(Object delivery);
    public List<Object> read();
    public void update(AbstractDelivery delivery);
    public void delete(String id);
    public AbstractDelivery search(String id);
}
