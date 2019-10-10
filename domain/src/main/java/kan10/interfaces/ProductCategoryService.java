package kan10.interfaces;

import kan10.entities.ProductCategory;
import java.util.List;

/**
 * Service interface class to manage Product Category
 * @author Suriya
 */
public interface ProductCategoryService {

    // list products categories
    List <ProductCategory>  getAllProductsCategories();

    // create product category
    boolean createProductCategory (ProductCategory productcategory);
}
