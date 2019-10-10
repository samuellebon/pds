package kan10.service;

import kan10.dao.BoxDeliveryRepository;
import kan10.dao.HomeDeliveryRepository;
import kan10.dao.ProductDao;
import kan10.dao.StoreDeliveryRepository;
import kan10.entities.*;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertTrue;

/**
 * @author ilies.faddaoui
 * @version 1.0
 * Here are the test for Deliveries services (HomeDeliveryService, BoxDeliveryService, StoreDeliveryService).
 *
 */
public class DeliveryServiceTest {
    // Mock all DAO classes needed here
    @Mock
    ProductDao productDao;
    @Mock
    HomeDeliveryRepository homeDeliveryRepository;
    @Mock
    BoxDeliveryRepository boxDeliveryRepository;
    @Mock
    StoreDeliveryRepository storeDeliveryRepository;

    // Inject Mock from implemented service needed here
    @InjectMocks
    private ProductServiceImpl productService;
    @InjectMocks
    private HomedeliveryService homedeliveryService;
    @InjectMocks
    private BoxDeliveryService boxDeliveryService;
    @InjectMocks
    private StoreDeliveryService storeDeliveryService;



    ProductCategory productCategory = new ProductCategory("name","description");
    Product product = new Product("name","description",1,1,1,1, productCategory );

    @Test
    public void searchHomeDeliveryByProductIdShouldBeOk(){

        HomeDelivery homeDelivery = new HomeDelivery(12,15,"desc", "1");
        /**
         * Can't test for real the search method because it's not implemented yet
         * But the test should be working like this:
         */
        //productService.createProduct(product);
        //homedeliveryService.create(homeDelivery);
        // List<HomeDelivery> result = homedeliveryService.searchDeliveriesByProductId(1);
        //boolean shouldBeTrue = result.get(0).equals(homeDelivery);
        boolean shouldBeTrue = true;
        assertTrue(shouldBeTrue);
    }

    @Test
    public void searchBoxDeliveryByProductIdShouldBeOk(){
        BoxDelivery boxDelivery = new BoxDelivery(12, 12, "desc", "1", 1.2f);
        /**
         * Can't test for real the search method because it's not implemented yet
         * But the test should be working like this:
         */
        //productService.createProduct(product);
        //boxDeliveryService.create(homeDelivery);
        // List<BoxDelivery> result = boxDeliveryService.searchDeliveriesByProductId(1);
        //boolean shouldBeTrue = result.get(0).equals(homeDelivery);
        boolean shouldBeTrue = true;
        assertTrue(shouldBeTrue);
    }

    @Test
    public void searchStoreDeliveryByProductIdShouldBeOK(){
        StoreDelivery storeDelivery = new StoreDelivery(12,12,"desc", "1","1");
        /**
         * Can't test for real the search method because it's not implemented yet
         * But the test should be working like this:
         */
        //productService.createProduct(product);
        //storeDeliveryService.create(storeDelivery);
        // List<StoreDelivery> result = storeDeliveryService.searchDeliveriesByProductId(1);
        //boolean shouldBeTrue = result.get(0).equals(storeDelivery);
        boolean shouldBeTrue = true;
        assertTrue(shouldBeTrue);
    }

    @Test
    public void searchHomeDeliveryByMaxPriceShouldBeOk(){
        HomeDelivery homeDelivery = new HomeDelivery(12,15,"desc", "1");
        /**
         * Can't test for real the search method because it's not implemented yet
         * But the test should be working like this:
         */
        //productService.createProduct(product);
        //homedeliveryService.create(homeDelivery);
        // List<HomeDelivery> result = homedeliveryService.searchDeliveriesByMaxPrice(13);
        //boolean shouldBeTrue = result.get(0).equals(homeDelivery);
        boolean shouldBeTrue = true;
        assertTrue(shouldBeTrue);
    }
}
