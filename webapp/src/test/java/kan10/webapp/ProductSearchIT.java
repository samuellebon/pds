package kan10.webapp;

import com.querydsl.core.BooleanBuilder;
import kan10.dao.ProductDao;
import kan10.entities.Product;
import kan10.entities.ProductCategory;
import kan10.predicate.ProductPredicate;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class ProductSearchIT extends AbstractIT {

    @Inject
    private ProductDao productDao;

    @Inject
    private EntityManagerFactory entityManagerFactory;

    private final String category1 = "category_1";
    private final String category2 = "category_2";
    private final String nonExistingCategory = "category_XXX";

    private final String productName1 = "name_1";
    private final String productName2 = "name_2";
    private final String productName3 = "name_3";
    private final String productName4 = "name_4";
    private final String nonExistingProductName = "name_XXX";

    private static boolean isDataInitialized = false;

    @Before
    public void init() {

        if (!isDataInitialized) {

            EntityManager em = entityManagerFactory.createEntityManager();
            EntityTransaction tx = em.getTransaction();
            tx.begin();

            ArrayList<ProductCategory> productCategories = new ArrayList<ProductCategory>() {{
                add(new ProductCategory(category1, "description_1"));
                add(new ProductCategory(category2, "description_2"));
            }};

            productCategories.forEach(em::persist);

            ArrayList<Product> products = new ArrayList<Product>() {{
                add(new Product(productName1, "description_1", 1, 1, 1, 1, productCategories.get(0)));
                add(new Product(productName2, "description_2", 1, 1, 1, 1, productCategories.get(0)));
                add(new Product(productName3, "description_3", 1, 1, 1, 1, productCategories.get(1)));
                add(new Product(productName4, "description_4", 1, 1, 1, 1, productCategories.get(1)));
            }};

            products.forEach(em::persist);

            tx.commit();
            em.close();
            
            isDataInitialized = true;
        }

    }

    @Test
    public void testPredicateProductHasExistingCategoryShouldReturnTwoResult() {

        List<Product> result = (List<Product>) productDao.findAll(ProductPredicate.hasCategory(category1));

        Assert.assertEquals(2, result.size());
        result.forEach(p -> Assert.assertEquals(category1, p.getProductCategory().getName()));
    }

    @Test
    public void testPredicateProductHasUnknowCategoryShouldReturnNoResult() {

        List<Product> result = (List<Product>) productDao.findAll(ProductPredicate.hasCategory(nonExistingCategory));

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testPredicateProductHasExistingNameShouldReturnUniqueResult() {
        List<Product> result = (List<Product>) productDao.findAll(ProductPredicate.hasName(productName1));

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(productName1, result.get(0).getName());
    }

    @Test
    public void testPredicateProductHasUnknowNameShouldReturnNoResult() {
        List<Product> result = (List<Product>) productDao.findAll(ProductPredicate.hasName(nonExistingProductName));

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testPredicateProductMatchCategoryAndNameShouldReturnUniqueResult() {

        BooleanBuilder bl = new BooleanBuilder();
        bl.and(ProductPredicate.hasCategory(category1));
        bl.and(ProductPredicate.hasName(productName1));

        List<Product> result = (List<Product>) productDao.findAll(bl);

        Assert.assertEquals(1, result.size());
        Assert.assertEquals(category1, result.get(0).getProductCategory().getName());
        Assert.assertEquals(productName1, result.get(0).getName());
    }

    @Test
    public void testPredicateProductNotMatchCategoryAndNameShouldReturnNoResult() {

        BooleanBuilder bl = new BooleanBuilder();
        bl.and(ProductPredicate.hasCategory(nonExistingCategory));
        bl.and(ProductPredicate.hasName(nonExistingProductName));

        List<Product> result = (List<Product>) productDao.findAll(bl);

        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testPredicateProductNameContainsCharSequenceShouldReturnFourResult() {

        List<Product> result = (List<Product>) productDao.findAll(ProductPredicate.hasName("name_"));

        Assert.assertEquals(4, result.size());
        result.forEach(p -> Assert.assertTrue(p.getName().contains("name_")));
    }

}