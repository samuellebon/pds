package kan10.dao;

import kan10.entities.StoreDelivery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
/**
 * @author Ilies Faddaoui
 * @version 1.0.0
 */
@Repository
public interface StoreDeliveryRepository extends JpaRepository<StoreDelivery, String> {
}
