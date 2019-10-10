package kan10.webapp;

import kan10.dao.OrderDao;
import kan10.entities.*;
import kan10.enums.Keywords;
import kan10.interfaces.LocationService;
import kan10.service.*;
import org.junit.Test;

import javax.inject.Inject;
import javax.persistence.EntityManagerFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class OrderServiceIT extends AbstractIT {

    @Inject
    OrderService orderService;

    @Inject
    OrderDao orderDao;

    @Inject
    ProductCategoryServiceImpl productCategoryService;

    @Inject
    ProductServiceImpl productService;

    @Inject
    LocationService locationService;

    @Inject
    StoreCategoryServiceImpl storeCategoryService;

    @Inject
    StoreServiceImpl storeService;

    @Inject
    StoreProductServiceImpl storeProductService;

    @Test
    public void createOrder_OK() {

        ProductCategory productCategory = new ProductCategory("name","description");
        productCategory.setKeywords(Keywords.getDistinct(new Random().nextInt(5)));
        productCategoryService.createProductCategory(productCategory);

        Product product = new Product("name", "description", 1,1,1,1, productCategory, Keywords.getOne());
        productService.createProduct(product);

        Location location = new Location(1,"aisle",1,1);
        locationService.saveLocationToDb(location);

        StoreCategory storeCategory = new StoreCategory("name", "description");
        storeCategoryService.saveStoreCategoryToDb(storeCategory);

        Store store = new Store("name",location, storeCategory);
        storeService.saveStoreToDb(store);

        StoreProduct storeProduct = new StoreProduct(product, store, 100, 10);
        storeProductService.createStoreProduct(storeProduct);

        List<OrderLine> orderLines = new ArrayList<OrderLine>(){{
            add(new OrderLine(storeProduct, 10));
        }};

        Order order = new Order();
        order.setOrderLines(orderLines);

        orderService.createOrder(order);

        List<Order> orders = orderDao.findAll();

        assertEquals(1, orders.size());

        Order actual = orders.get(0);

        assertEquals(1, actual.getOrderLines().size());
        assertEquals(orderLines, actual.getOrderLines());
        assertEquals(new Long(1), actual.getId());
        assertEquals(product, actual.getOrderLines().get(0).getStoreProduct().getProduct());
        assertEquals(store, actual.getOrderLines().get(0).getStoreProduct().getStore());

    }

}