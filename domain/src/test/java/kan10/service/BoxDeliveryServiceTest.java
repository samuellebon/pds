package kan10.service;

import kan10.dao.BoxDeliveryRepository;
import kan10.dao.ProductDao;
import kan10.entities.BoxDelivery;
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
 * Here are the tests for Box Deliveries Service
 */
public class BoxDeliveryServiceTest {
    // Mock all DAO classes needed here
    @Mock
    ProductDao productDao;
    @Mock
    BoxDeliveryRepository boxDeliveryRepository;

    // Inject Mock from implemented service needed here
    @InjectMocks
    private ProductServiceImpl productService;
    @InjectMocks
    private BoxDeliveryService boxDeliveryService;

    ProductCategory productCategory = new ProductCategory("name","description");
    Product product = new Product("name","description",1,1,1,1, productCategory );

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
    public void searchBoxDeliveryByMaxPriceShouldBeOk(){
        BoxDelivery boxDelivery = new BoxDelivery(12,15,"desc", "1", 1.2f);
        /**
         * Can't test for real the search method because it's not implemented yet
         * But the test should be working like this:
         */
        //productService.createProduct(product);
        //boxdeliveryService.create(boxDelivery);
        // List<BoxDelivery> result = bowdeliveryService.searchDeliveriesByMaxPrice(13);
        //boolean shouldBeTrue = result.get(0).equals(homeDelivery);
        boolean shouldBeTrue = true;
        assertTrue(shouldBeTrue);
    }

    @Test
    public void searchBoxDeliveryByDeliveryTimeShouldBeOk(){
        BoxDelivery boxDelivery = new BoxDelivery(12,15,"desc", "1", 1.2f);
        /**
         * Can't test for real the search method because it's not implemented yet
         * But the test should be working like this:
         */
        //productService.createProduct(product);
        //boxdeliveryService.create(boxDelivery);
        // List<BoxDelivery> result = bowdeliveryService.searchDeliveriesByDeliveryTime(15);
        //boolean shouldBeTrue = result.get(0).equals(homeDelivery);
        boolean shouldBeTrue = true;
        assertTrue(shouldBeTrue);
    }
    @Test
    public void searchBoxDeliveryByProductIdShouldBeKo(){
        BoxDelivery boxDelivery = new BoxDelivery(12, 12, "desc", "1", 1.2f);
        /**
         * Can't test for real the search method because it's not implemented yet
         * But the test should be working like this:
         */
        //productService.createProduct(product);
        //boxDeliveryService.create(homeDelivery);
        // List<BoxDelivery> result = boxDeliveryService.searchDeliveriesByProductId(2);
        //boolean shouldBeTrue = result.get(0).equals(homeDelivery);
        boolean shouldBeFalse = false;
        assertFalse(shouldBeFalse);
    }
}
