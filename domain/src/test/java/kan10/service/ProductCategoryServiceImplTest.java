package kan10.service;

import kan10.dao.ProductCategoryDao;
import kan10.entities.ProductCategory;
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

public class ProductCategoryServiceImplTest {

    @Mock
    ProductCategoryDao productCategoryDao;

    @InjectMocks
    private ProductCategoryServiceImpl productCategoryService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createProductCategoryWithInvalidCharactersShouldBeKO() {

        ProductCategory productCategory = new ProductCategory("##%","description");
        boolean shouldBeKO = productCategoryService.createProductCategory(productCategory);

        assertFalse(shouldBeKO);
    }

    @Test
    public void createProductCategoryWithNullNameShouldBeKO() {

        ProductCategory productCategory = new ProductCategory(null,"description");
        boolean shouldBeKO = productCategoryService.createProductCategory(productCategory);

        assertFalse(shouldBeKO);
    }

    @Test
    public void createProductCategoryWithNullDescriptionShouldBeKO() {

        ProductCategory productCategory = new ProductCategory("name",null);
        boolean shouldBeKO = productCategoryService.createProductCategory(productCategory);

        assertFalse(shouldBeKO);
    }

    @Test
    public void createProductCategoryWhichIsNullShouldBeKO() {

        boolean shouldBeKO = productCategoryService.createProductCategory(null);

        assertFalse(shouldBeKO);
    }

    @Test
    public void createProductCategoryWhichAlreadyExistShouldBeKO() {

        ProductCategory productCategory = new ProductCategory("name","description");

        ProductCategory existingProductCategoryInDatabase = new ProductCategory("name","description");

        Mockito.when(productCategoryDao.findByName(productCategory.getName())).thenReturn(existingProductCategoryInDatabase);

        boolean shouldBeKO = productCategoryService.createProductCategory(productCategory);

        assertFalse(shouldBeKO);
    }

    @Test
    public void createProductCategoryWithRightValuesShouldBeOK() {

        ProductCategory productCategory = new ProductCategory("name","description");

        boolean shouldBeOK = productCategoryService.createProductCategory(productCategory);

        assertTrue(shouldBeOK);
    }

    @Test
    public void getAllProductsCategories() {
        List<ProductCategory> productCategoryList = productCategoryService.getAllProductsCategories();
        assertNotNull(productCategoryList);
    }
}