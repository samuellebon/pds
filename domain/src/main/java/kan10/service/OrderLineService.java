package kan10.service;

import kan10.dao.OrderLineDao;
import kan10.entities.OrderLine;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderLineService {

    private final OrderLineDao orderLineDao;

    public OrderLineService(OrderLineDao orderLineDao) {
        this.orderLineDao = orderLineDao;
    }

    List<OrderLine> getAllOrderLines() {
        return orderLineDao.findAll();
    }
}
