package kan10.service;

import kan10.StringUtil;
import kan10.dao.ProductCategoryDao;
import kan10.entities.ProductCategory;

import kan10.interfaces.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Service class to manage Product Category
 * @author Suriya
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {

    @Autowired
    private ProductCategoryDao productCategoryRepository;

    @Override
    public List<ProductCategory> getAllProductsCategories() {
        return productCategoryRepository.findAll();
    }

    @Override
    public boolean createProductCategory (ProductCategory productcategory) {

        try {

            if (productcategory == null) return false;
            else if (productcategory.getName() == null || productcategory.getDescription() == null) return false;

            if (StringUtil.containsInvalidCharacter(productcategory.getName())) return false;

            ProductCategory isAlreadyInDb = productCategoryRepository.findByName(productcategory.getName());

            if (isAlreadyInDb != null) return false;

            productCategoryRepository.save(productcategory);
        } catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }

}
