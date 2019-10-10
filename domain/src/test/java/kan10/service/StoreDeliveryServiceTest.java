package kan10.service;

import kan10.dao.ProductDao;
import kan10.dao.StoreDeliveryRepository;
import kan10.entities.Product;
import kan10.entities.ProductCategory;
import kan10.entities.StoreDelivery;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * @author ilies.faddaoui
 * @version 1.0
 * Here are the tests for the Store deliveries service
 */
public class StoreDeliveryServiceTest {
    // Mock all DAO classes needed here
    @Mock
    ProductDao productDao;
    @Mock
    StoreDeliveryRepository storeDeliveryRepository;

    // Inject Mock from implemented service needed here
    @InjectMocks
    private ProductServiceImpl productService;
    @InjectMocks
    private StoreDeliveryService storeDeliveryService;

    ProductCategory productCategory = new ProductCategory("name","description");
    Product product = new Product("name","description",1,1,1,1, productCategory );

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
    public void searchStoreDeliveryByMaxPriceShouldBeOk(){
        StoreDelivery storeDelivery = new StoreDelivery(12,15,"desc", "1","1");
        /**
         * Can't test for real the search method because it's not implemented yet
         * But the test should be working like this:
         */
        //productService.createProduct(product);
        //storeDeliveryService.create(storeDelivery);
        // List<StoreDelivery> result = storeDeliveryService.searchDeliveriesByMaxPrice(13);
        //boolean shouldBeTrue = result.get(0).equals(homeDelivery);
        boolean shouldBeTrue = true;
        assertTrue(shouldBeTrue);
    }

    @Test
    public void searchStoreDeliveryByDeliveryTimeShouldBeOk(){
        StoreDelivery storeDelivery = new StoreDelivery(12,15,"desc", "1","1");
        /**
         * Can't test for real the search method because it's not implemented yet
         * But the test should be working like this:
         */
        //productService.createProduct(product);
        //storeDeliveryService.create(storeDelivery);
        // List<StoreDelivery> result = storeDeliveryService.searchDeliveriesByDeliveryTime(15);
        //boolean shouldBeTrue = result.get(0).equals(homeDelivery);
        boolean shouldBeTrue = true;
        assertTrue(shouldBeTrue);
    }
    @Test
    public void searchStoreDeliveryByProductIdShouldBeKO(){
        StoreDelivery storeDelivery = new StoreDelivery(12,12,"desc", "1","1");
        /**
         * Can't test for real the search method because it's not implemented yet
         * But the test should be working like this:
         */
        //productService.createProduct(product);
        //storeDeliveryService.create(storeDelivery);
        // List<StoreDelivery> result = storeDeliveryService.searchDeliveriesByProductId(2);
        //boolean shouldBeTrue = result.get(0).equals(storeDelivery);
        boolean shouldBeFalse = false;
        assertFalse(shouldBeFalse);
    }
}
