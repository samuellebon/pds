package kan10.service;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import kan10.StringUtil;
import kan10.dao.ProductDao;
import kan10.entities.Product;

import kan10.interfaces.ProductService;
import kan10.predicate.ProductPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class to manage Product
 *
 * @author Suriya, Samuel
 */
@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productRepository;

    @Override
    public boolean createProduct(Product product) {

        try {

            if (product == null) return false;
            else if (product.getName() == null || product.getDescription() == null || product.getHeight() <= 0 || product.getLength() <= 0 || product.getWeight() <= 0 || product.getWidth() <= 0 || product.getProductCategory() == null)
                return false;

            if (StringUtil.containsInvalidCharacter(product.getName())) return false;

            Product isAlreadyInDb = productRepository.findByName(product.getName());

            if (isAlreadyInDb != null) return false;

            productRepository.save(product);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }


    @Override
    public BooleanExpression hasName(String name) {
        return ProductPredicate.hasName(name);
    }

    @Override
    public BooleanExpression hasCategory(String category) {
        return ProductPredicate.hasCategory(category);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public DataTablesOutput<Product> findAll(DataTablesInput input, Predicate predicate) {
        return productRepository.findAll(input, predicate);
    }

    @Override
    public DataTablesOutput<Product> findAll(DataTablesInput input) {
        return productRepository.findAll(input);
    }

}