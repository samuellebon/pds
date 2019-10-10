package kan10.interfaces;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import kan10.entities.Product;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

/**
 * Service interface class to manage Product
 *
 * @author Suriya
 */
public interface ProductService {

    List<Product> getAllProducts();

    boolean createProduct(Product product);

    BooleanExpression hasName(String name);

    BooleanExpression hasCategory(String category);

    DataTablesOutput<Product> findAll(DataTablesInput input, Predicate predicate);

    DataTablesOutput<Product> findAll(DataTablesInput input);
}