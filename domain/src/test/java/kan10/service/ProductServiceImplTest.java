package kan10.service;

import kan10.dao.ProductDao;
import kan10.entities.Product;
import kan10.entities.ProductCategory;
import kan10.entities.QProduct;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Testing ProductServiceImpl class
 * Not a lot of mock because predicate ain't really (easily) mockeable
 * @author Suriya, Samuel
 */
public class ProductServiceImplTest {
    // Mocking Product DAO
    @Mock
    ProductDao productDao;

    // Injecting mock in tested service : ProductServiceImpl
    @InjectMocks
    private ProductServiceImpl productService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    // Init mockito annotations "manually"
    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createProductWithNegativeNumberShouldBeKO() {
        ProductCategory productCategory = new ProductCategory("name","description");
        Product product = new Product("name","description",-1,-1,-1,-1, productCategory );
        boolean shouldBeKO = productService.createProduct(product);
        assertFalse(shouldBeKO);
    }

    @Test
    public void createProductWithInvalidCharactersShouldBeKO() {
        ProductCategory productCategory = new ProductCategory("name","description");
        Product product = new Product("#&!","description",1,1,1,1, productCategory );
        boolean shouldBeKO = productService.createProduct(product);
        assertFalse(shouldBeKO);

    }

    @Test
    public void createProductWhichAlreadyExistingShouldBeKO() {
        ProductCategory productCategory = new ProductCategory("name","description");
        Product product = new Product("name","description",1,1,1,1, productCategory );
        Product existingProductInDatabase = new Product("name","description",1,1,1,1, productCategory );
        Mockito.when(productDao.findByName(product.getName())).thenReturn(existingProductInDatabase);
        boolean shouldBeKO = productService.createProduct(product);
        assertFalse(shouldBeKO);
    }

    @Test
    public void createProductWhichIsNullShouldBeKO() {
        boolean shouldBeKO = productService.createProduct(null);
        assertFalse(shouldBeKO);
    }

    @Test
    public void createProductWithNullCategoryShouldBeKO() {
        Product product = new Product("name","description",1,1,1,1, null);
        boolean shouldBeKO = productService.createProduct(product);
        assertFalse(shouldBeKO);
    }

    @Test
    public void createProductWithNullNameShouldBeKO() {
        ProductCategory productCategory = new ProductCategory("name","description");
        Product product = new Product(null,"description",1,1,1,1, productCategory);
        boolean shouldBeKO = productService.createProduct(product);
        assertFalse(shouldBeKO);
    }

    @Test
    public void createProductWithNullDescriptionShouldBeKO() {
        ProductCategory productCategory = new ProductCategory("name","description");
        Product product = new Product("name",null,1,1,1,1, productCategory);
        boolean shouldBeKO = productService.createProduct(product);
        assertFalse(shouldBeKO);
    }

    @Test
    public void createProductWithRightValuesShouldBeOK() {
        ProductCategory productCategory = new ProductCategory("name","description");
        Product product = new Product("name","description",1,1,1,1, productCategory );
        boolean shouldBeOK = productService.createProduct(product);
        assertTrue(shouldBeOK);
    }

    @Test
    public void getAllProducts() {
        List<Product> productList = productService.getAllProducts();
        assertNotNull(productList);
    }

    @Test
    public void hasName() {
        assertEquals(QProduct.product.name.containsIgnoreCase("name"),productService.hasName("name"));
    }

    @Test
    public void hasNameNullOrEmpty() {
        assertEquals(QProduct.product.isNotNull(),productService.hasName(null));
        assertEquals(QProduct.product.isNotNull(),productService.hasName(""));
    }

    @Test
    public void hasCategory() {
        assertEquals(QProduct.product.productCategory.name.eq("category_name"),productService.hasCategory("category_name"));
    }

    @Test
    public void hasCategoryNullOrEmpty() {
        assertEquals(QProduct.product.isNotNull(),productService.hasCategory(null));
        assertEquals(QProduct.product.isNotNull(),productService.hasCategory(""));
    }
}