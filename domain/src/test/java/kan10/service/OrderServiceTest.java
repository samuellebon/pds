package kan10.service;

import kan10.dao.OrderDao;
import kan10.entities.*;
import kan10.enums.Keywords;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {

    @Mock
    OrderDao orderDao;

    @Test
    public void createOrder_OK() {

        OrderService service = new OrderService(orderDao);
        OrderService orderService = Mockito.spy(service);

        ProductCategory productCategory = new ProductCategory("name","description");
        productCategory.setKeywords(Keywords.getDistinct(new Random().nextInt(5)));

        Product product = new Product("name", "description", 1,1,1,1, productCategory, Keywords.getOne());

        Location location = new Location(1,"aisle",1,1);

        StoreCategory storeCategory = new StoreCategory("name", "description");

        Store store = new Store("name",location, storeCategory);

        StoreProduct storeProduct = new StoreProduct(product, store, 100, 10);

        List<OrderLine> orderLines = new ArrayList<OrderLine>(){{
            add(new OrderLine(storeProduct, 10));
        }};

        LocalDateTime orderDate = LocalDateTime.now();
        Order order = new Order();
        order.setOrderLines(orderLines);

        List<Order> orderList = spy(new ArrayList<>());

        doAnswer((i) -> {
                orderList.add(order);
            return null;
        }).when(orderDao).save(order);

        when(orderDao.findAll()).thenReturn(orderList);

        orderService.createOrder(order);
        verify(orderList).add(order);

        List<Order> orders = orderDao.findAll();

        assertEquals(1, orders.size());

        Order actual = orders.get(0);

        assertTrue(orderDate.isBefore(actual.getDate()) || orderDate.isEqual(actual.getDate()));
        assertEquals(1, actual.getOrderLines().size());
        assertEquals(orderLines, actual.getOrderLines());
        assertEquals(product, actual.getOrderLines().get(0).getStoreProduct().getProduct());
        assertEquals(store, actual.getOrderLines().get(0).getStoreProduct().getStore());

    }

}