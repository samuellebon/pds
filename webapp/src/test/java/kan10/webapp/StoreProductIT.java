package kan10.webapp;

import com.querydsl.core.BooleanBuilder;
import kan10.dao.StoreProductDao;
import kan10.entities.*;
import kan10.predicate.StoreProductPredicate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class StoreProductIT extends AbstractIT {

    @Inject
    StoreProductDao storeProductDao;

    @Inject
    EntityManagerFactory entityManagerFactory;

    private final String category1 = "category_1";
    private final String category2 = "category_2";
    private final String category3 = "category_3";

    private final String productName1 = "name_1";
    private final String productName2 = "name_2";
    private final String productName3 = "name_3";
    private final String productName4 = "name_4";
    private final String productName5 = "name_5";
    private final String nonExistingProductName = "name_XXX";

    private final String storeName1 = "store_1";
    private final String storeName2 = "store_2";
    private final String storeName3 = "store_3";
    private final String storeName4 = "store_4";

    private static boolean isDataInitialized = false;

    @Before
    public void databaseInitialization() {

        if (!isDataInitialized) {

            EntityManager em = entityManagerFactory.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            ArrayList<Location> locations = new ArrayList<Location>() {{
                add(new Location(0, "aisle_1", 10, 1000));
                add(new Location(0, "aisle_2", 20, 2000));
                add(new Location(1, "aisle_3", 30, 3000));
            }};

            locations.forEach(em::persist);

            ArrayList<StoreCategory> storeCategories = new ArrayList<StoreCategory>() {{
                add(new StoreCategory("StoreCategory_1", "Description_1"));
                add(new StoreCategory("StoreCategory_2", "Description_2"));
            }};

            storeCategories.forEach(em::persist);


            ArrayList<Store> stores = new ArrayList<Store>() {{
                add(new Store(storeName1, locations.get(0), storeCategories.get(0)));
                add(new Store(storeName2, locations.get(1), storeCategories.get(1)));
                add(new Store(storeName3, locations.get(2), storeCategories.get(0)));
                add(new Store(storeName4, locations.get(2), storeCategories.get(1)));
            }};

            stores.forEach(em::persist);


            ArrayList<ProductCategory> productCategories = new ArrayList<ProductCategory>() {{
                add(new ProductCategory(category1, "description_1"));
                add(new ProductCategory(category2, "description_2"));
                add(new ProductCategory(category3, "description_3"));
            }};

            productCategories.forEach(em::persist);


            ArrayList<Product> products = new ArrayList<Product>() {{
                add(new Product(productName1, "description_1", 1, 1, 1, 1, productCategories.get(0)));
                add(new Product(productName2, "description_2", 1, 1, 1, 1, productCategories.get(0)));
                add(new Product(productName3, "description_3", 1, 1, 1, 1, productCategories.get(1)));
                add(new Product(productName4, "description_4", 1, 1, 1, 1, productCategories.get(1)));
                add(new Product(productName5, "description_5", 1, 1, 1, 1, productCategories.get(2)));
            }};

            products.forEach(em::persist);


            ArrayList<StoreProduct> storeProducts = new ArrayList<StoreProduct>() {{
                add(new StoreProduct(products.get(0), stores.get(0), 10, 100));
                add(new StoreProduct(products.get(0), stores.get(0), 10, 950));
                add(new StoreProduct(products.get(1), stores.get(1), 10, 2000));
                add(new StoreProduct(products.get(2), stores.get(2), 10, 10));
                add(new StoreProduct(products.get(3), stores.get(1), 10, 300));
            }};

            storeProducts.forEach(em::persist);

            tx.commit();
            em.close();

            isDataInitialized = true;
        }

    }


    @Test
    public void testStoreProductSearchWithProductName1ShouldReturnTwoResult() {

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(StoreProductPredicate.hasName(productName1));

        Assert.assertEquals(2, result.size());
        result.forEach(sp -> helper.hasName(sp, productName1));

    }

    @Test
    public void testStoreProductSearchWithProductName2ShouldReturnUniqueResult() {

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(StoreProductPredicate.hasName(productName2));

        Assert.assertEquals(1, result.size());
        Assert.assertTrue(helper.hasName(result.get(0), productName2));

    }

    @Test
    public void testStoreProductSearchWithNonExistingProductNameShouldReturnNoResult() {

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(StoreProductPredicate.hasName(nonExistingProductName));

        Assert.assertEquals(0, result.size());

    }

    @Test
    public void testStoreProductSearchWithProductNameContainsNameShouldReturnFiveResult() {

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(StoreProductPredicate.hasName("name"));

        Assert.assertEquals(5, result.size());
        result.forEach(sp -> Assert.assertTrue(helper.hasName(sp, "name")));

    }

    @Test
    public void testStoreProductSearchWithPriceGreaterThan1000ShouldReturnUniqueResult() {

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(StoreProductPredicate.priceGreaterOrEqual(1000));

        Assert.assertEquals(1, result.size());
        Assert.assertTrue(helper.hasPriceGoe(result.get(0), 1000));

    }

    @Test
    public void testStoreProductSearchWithPriceLesserThan500ShouldReturnThreeResult() {

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(StoreProductPredicate.priceLesserOrEqual(500));

        Assert.assertEquals(3, result.size());
        result.forEach(sp -> Assert.assertTrue(helper.hasPriceLoe(sp, 500)));

    }

    @Test
    public void testStoreProductSearchWithPriceBetween50and1500ShouldReturnThreeResult() {

        BooleanBuilder bl = new BooleanBuilder();
        bl.and(StoreProductPredicate.priceLesserOrEqual(1500));
        bl.and(StoreProductPredicate.priceGreaterOrEqual(50));

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(bl);

        Assert.assertEquals(3, result.size());
        result.forEach(sp -> Assert.assertTrue(helper.hasPriceBetween(sp, 50, 1500)));

    }

    @Test
    public void testStoreProductSearchWithInvertedPriceBetween1500and50ShouldReturnNoResult() {

        BooleanBuilder bl = new BooleanBuilder();
        bl.and(StoreProductPredicate.priceLesserOrEqual(50));
        bl.and(StoreProductPredicate.priceGreaterOrEqual(1500));

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(bl);

        Assert.assertEquals(0, result.size());

    }

    @Test
    public void testStoreProductSearchFromStore1ShouldReturnTwoResult() {

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(StoreProductPredicate.hasStore(storeName1));

        Assert.assertEquals(2, result.size());
        result.forEach(sp -> Assert.assertTrue(helper.hasStore(sp, storeName1)));

    }

    @Test
    public void testStoreProductSearchFromStore4ShouldReturnNoResult() {

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(StoreProductPredicate.hasStore(storeName4));

        Assert.assertEquals(0, result.size());

    }

    @Test
    public void testStoreProductSearchWithCategory1ShouldReturnThreeResult() {

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(StoreProductPredicate.hasCategory(category1));

        Assert.assertEquals(3, result.size());
        result.forEach(sp -> Assert.assertTrue(helper.hasCategory(sp, category1)));

    }

    @Test
    public void testStoreProductSearchWithCategory3ShouldReturnNoResult() {

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(StoreProductPredicate.hasCategory(category3));

        Assert.assertEquals(0, result.size());

    }

    @Test // Should return the first StoreProduct
    public void testStoreProductMultiCriteriaSearchShouldReturnUniqueResult() {

        BooleanBuilder bl = new BooleanBuilder();
        bl.and(StoreProductPredicate.hasCategory(category1));
        bl.and(StoreProductPredicate.priceLesserOrEqual(100));
        bl.and(StoreProductPredicate.hasStore(storeName1));

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(bl);

        StoreProduct sp = result.get(0);

        Assert.assertEquals(1, result.size());
        Assert.assertTrue(helper.hasCategory(sp, category1));
        Assert.assertTrue(helper.hasPriceLoe(sp, 100));
        Assert.assertTrue(helper.hasStore(sp, storeName1));

    }

    @Test // Should return the fourth and fifth StoreProduct
    public void testStoreProductMultiCriteriaSearchShouldReturnTwoResult_4and5() {

        BooleanBuilder bl = new BooleanBuilder();
        bl.and(StoreProductPredicate.hasCategory(category2));
        bl.and(StoreProductPredicate.priceLesserOrEqual(300));
        bl.and(StoreProductPredicate.priceGreaterOrEqual(5));

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(bl);

        Assert.assertEquals(2, result.size());
        result.forEach(sp -> {
            Assert.assertTrue(helper.hasCategory(sp, category2));
            Assert.assertTrue(helper.hasPriceLoe(sp, 300));
            Assert.assertTrue(helper.hasPriceGoe(sp, 5));
        });

    }

    @Test // Should return the first and second StoreProduct
    public void testStoreProductMultiCriteriaSearchShouldReturnTwoResult_1and2() {

        BooleanBuilder bl = new BooleanBuilder();
        bl.and(StoreProductPredicate.hasCategory(category1));
        bl.and(StoreProductPredicate.hasStore(storeName1));

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(bl);

        Assert.assertEquals(2, result.size());
        result.forEach(sp -> {
            Assert.assertTrue(helper.hasCategory(sp, category1));
            Assert.assertTrue(helper.hasStore(sp, storeName1));
        });

    }

    @Test // Should return the first, second and third StoreProduct
    public void testStoreProductMultiCriteriaSearchShouldReturnThreeResult_1and2and3() {

        BooleanBuilder bl = new BooleanBuilder();
        bl.and(StoreProductPredicate.hasName("name"));
        bl.and(StoreProductPredicate.hasCategory(category1));

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(bl);

        Assert.assertEquals(3, result.size());
        result.forEach(sp -> {
            Assert.assertTrue(helper.hasName(sp, "name"));
            Assert.assertTrue(helper.hasCategory(sp, category1));
        });

    }

    @Test // Should return the third StoreProduct
    public void testStoreProductAllCriteriaSearchShouldReturnUniqueResult() {

        BooleanBuilder bl = new BooleanBuilder();
        bl.and(StoreProductPredicate.hasName("na"));
        bl.and(StoreProductPredicate.hasCategory(category1));
        bl.and(StoreProductPredicate.priceGreaterOrEqual(1500));
        bl.and(StoreProductPredicate.priceLesserOrEqual(2000));
        bl.and(StoreProductPredicate.hasStore(storeName2));

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(bl);

        Assert.assertEquals(1, result.size());

        StoreProduct sp = result.get(0);

        Assert.assertTrue(helper.hasName(sp, "na"));
        Assert.assertTrue(helper.hasCategory(sp, category1));
        Assert.assertTrue(helper.hasPriceGoe(sp, 1500));
        Assert.assertTrue(helper.hasPriceLoe(sp, 2000));
        Assert.assertTrue(helper.hasStore(sp, storeName2));

    }

    @Test
    public void testStoreProductAllCriteriaSearchShouldReturnNoResult() {

        BooleanBuilder bl = new BooleanBuilder();
        bl.and(StoreProductPredicate.hasName("n"));
        bl.and(StoreProductPredicate.hasCategory(category1));
        bl.and(StoreProductPredicate.priceGreaterOrEqual(1000));
        bl.and(StoreProductPredicate.priceLesserOrEqual(5000));
        bl.and(StoreProductPredicate.hasStore(storeName1));

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(bl);

        Assert.assertEquals(0, result.size());

    }

    @Test
    public void testStoreProductAllValidCriteriaSearchButNonExistingNameShouldReturnNoResult() {

        BooleanBuilder bl = new BooleanBuilder();
        bl.and(StoreProductPredicate.hasName(nonExistingProductName));
        bl.and(StoreProductPredicate.hasCategory(category1));
        bl.and(StoreProductPredicate.priceGreaterOrEqual(5));
        bl.and(StoreProductPredicate.priceLesserOrEqual(2000));
        bl.and(StoreProductPredicate.hasStore(storeName3));

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(bl);

        Assert.assertEquals(0, result.size());

    }

    @Test
    public void testStoreProductAllValidCriteriaSearchButInvalidStoreShouldReturnNoResult() {

        BooleanBuilder bl = new BooleanBuilder();
        bl.and(StoreProductPredicate.hasName("na"));
        bl.and(StoreProductPredicate.hasCategory(category1));
        bl.and(StoreProductPredicate.priceGreaterOrEqual(5));
        bl.and(StoreProductPredicate.priceLesserOrEqual(2000));
        bl.and(StoreProductPredicate.hasStore(storeName4));

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(bl);

        Assert.assertEquals(0, result.size());

    }

    @Test
    public void testStoreProductAllInvalidValidCriteriaShouldReturnNoResult() {

        BooleanBuilder bl = new BooleanBuilder();
        bl.and(StoreProductPredicate.hasName("&$*#§"));
        bl.and(StoreProductPredicate.hasCategory(category3));
        bl.and(StoreProductPredicate.priceGreaterOrEqual(1000));
        bl.and(StoreProductPredicate.priceLesserOrEqual(500));
        bl.and(StoreProductPredicate.hasStore(storeName4));

        List<StoreProduct> result = (List<StoreProduct>) storeProductDao.findAll(bl);

        Assert.assertEquals(0, result.size());

    }


    // Helper interface for assertion
    private interface StoreProductHelper {

        boolean hasPriceBetween(StoreProduct sp, double a, double b);

        boolean hasPriceLoe(StoreProduct sp, double a);

        boolean hasPriceGoe(StoreProduct sp, double a);

        boolean hasName(StoreProduct sp, String s);

        boolean hasCategory(StoreProduct sp, String s);

        boolean hasStore(StoreProduct sp, String s);

    }

    private StoreProductHelper helper = new StoreProductHelper() {
        @Override
        public boolean hasPriceBetween(StoreProduct sp, double a, double b) {
            return (sp.getPrice() >= a) && (sp.getPrice() <= b);
        }

        @Override
        public boolean hasPriceLoe(StoreProduct sp, double a) {
            return (sp.getPrice() <= a);
        }

        @Override
        public boolean hasPriceGoe(StoreProduct sp, double a) {
            return (sp.getPrice() >= a);
        }

        @Override
        public boolean hasName(StoreProduct sp, String s) {
            return sp.getProduct().getName().contains(s);
        }

        @Override
        public boolean hasCategory(StoreProduct sp, String s) {
            return sp.getProduct().getProductCategory().getName().contains(s);
        }

        @Override
        public boolean hasStore(StoreProduct sp, String s) {
            return sp.getStore().getLabel().contains(s);
        }
    };

}
