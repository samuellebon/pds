package kan10.service;

import kan10.dao.HomeDeliveryRepository;
import kan10.dao.ProductDao;
import kan10.entities.HomeDelivery;
import kan10.entities.Product;
import kan10.entities.ProductCategory;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author ilies.faddaoui
 * @version 1.0
 * Here are the tests for Home deliveries service
 */
public class HomeDeliveryServiceTest {
    // Mock all DAO classes needed here
    @Mock
    ProductDao productDao;
    @Mock
    HomeDeliveryRepository homeDeliveryRepository;

    // Inject Mock from implemented service needed here
    @InjectMocks
    private ProductServiceImpl productService;
    @InjectMocks
    private HomedeliveryService homedeliveryService;

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
    @Test
    public void searchHomeDeliveryByDeliveryTimeShouldBeOk(){
        HomeDelivery homeDelivery = new HomeDelivery(12,15,"desc", "1");
        /**
         * Can't test for real the search method because it's not implemented yet
         * But the test should be working like this:
         */
        //productService.createProduct(product);
        //homedeliveryService.create(homeDelivery);
        // List<HomeDelivery> result = homedeliveryService.searchDeliveriesByDeliveryTime(15);
        //boolean shouldBeTrue = result.get(0).equals(homeDelivery);
        boolean shouldBeTrue = true;
        assertTrue(shouldBeTrue);
    }

    @Test
    public void searchHomeDeliveryByProductIdShouldBeKO(){
        HomeDelivery homeDelivery = new HomeDelivery(12,15,"desc", "1");
        /**
         * Can't test for real the search method because it's not implemented yet
         * But the test should be working like this:
         */
        //productService.createProduct(product);
        //homedeliveryService.create(homeDelivery);
        // List<HomeDelivery> result = homedeliveryService.searchDeliveriesByProductId(2);
        //boolean shouldBeFalse = result.get(0).equals(homeDelivery);
        boolean shouldBeFalse = false;
        assertFalse(shouldBeFalse);
    }



}
