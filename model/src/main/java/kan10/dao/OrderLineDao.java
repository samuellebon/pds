package kan10.dao;

import kan10.entities.OrderLine;
import kan10.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderLineDao extends JpaRepository<OrderLine, Long> {
}
