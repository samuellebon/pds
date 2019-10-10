package kan10.service;

import kan10.dao.OrderDao;
import kan10.entities.Order;
import kan10.interfaces.IOrderService;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class OrderService implements IOrderService {

    private final
    OrderDao orderDao;

    @Inject
    public OrderService(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public void createOrder(Order order) {
        orderDao.save(order);
    }
}