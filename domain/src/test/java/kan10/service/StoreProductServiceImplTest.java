/*
package kan10.service;

import kan10.dao.StoreProductDao;
import kan10.entities.QProduct;
import kan10.entities.QStoreProduct;
import kan10.entities.StoreProduct;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.ArrayList;
import static org.junit.Assert.*;


*/
/**
 * Testing StoreProductServiceImpl class
 * Not a lot of mock because predicate ain't really (easily) mockeable
 * @author Suriya
 *//*

public class StoreProductServiceImplTest {
    // Mocking StoreProduct DAO
    @Mock
    StoreProductDao storeProductDao;

    // Injecting mock in tested service : StoreProductServiceImpl
    @InjectMocks
    private StoreProductServiceImpl storeProductService;

    // Init mockito annotations "manually"
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void hasName() {
        // Testing the service is calling the right predicate function with the right argument
        assertEquals(QStoreProduct.storeProduct.product.name.containsIgnoreCase("name"),storeProductService.hasName("name"));
    }

    @Test
    public void hasNameNullOrEmpty() {
        // Testing when null given
        assertEquals(QProduct.product.isNotNull(),storeProductService.hasName(null));
        // Testing when empty string given
        assertEquals(QProduct.product.isNotNull(),storeProductService.hasName(""));
    }

    @Test
    public void hasCategory() {
        // Testing the service is calling the right predicate function with the right argument
        assertEquals(QStoreProduct.storeProduct.product.productCategory.name.eq("category_name"),storeProductService.hasCategory("category_name"));
    }

    @Test
    public void hasCategoryNullOrEmpty() {
        // Testing when null given
        assertEquals(QProduct.product.isNotNull(),storeProductService.hasCategory(null));
        // Testing when empty string given
        assertEquals(QProduct.product.isNotNull(),storeProductService.hasCategory(""));
    }

    @Test
    public void hasStore() {
        // Testing the service is calling the right predicate function with the right argument
        assertEquals(QStoreProduct.storeProduct.store.label.eq("store_name"),storeProductService.hasStore("store_name"));
    }

    @Test
    public void priceGreaterOrEqual() {
        // Testing the service is calling the right predicate function with the right argument
        assertEquals(QStoreProduct.storeProduct.price.finalPrice.goe(10),storeProductService.priceGreaterOrEqual(10));
    }

    @Test
    public void priceLesserOrEqual() {
        // Testing the service is calling the right predicate function with the right argument
        assertEquals(QStoreProduct.storeProduct.price.finalPrice.loe(10),storeProductService.priceLesserOrEqual(10));
    }

    @Test
    public void getAllStoreProducts() {
        // Creating fake list
        ArrayList <StoreProduct> l = new ArrayList();
        StoreProduct sp1 = new StoreProduct();
        StoreProduct sp2 = new StoreProduct();
        l.add(sp1);l.add(sp2);

        // stubbing
        Mockito.when(storeProductDao.findAll()).thenReturn(l);

        // testing
        assertEquals(l, storeProductService.getAllStoreProducts());

        // verifying
        Mockito.verify(this.storeProductDao, Mockito.times(1)).findAll();
    }
}*/
