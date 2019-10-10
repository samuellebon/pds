package kan10.interfaces;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import kan10.entities.StoreProduct;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface StoreProductService {

    BooleanExpression hasName(String name);

    BooleanExpression hasCategory(String category);

    BooleanExpression hasStore(String store);

    DataTablesOutput<StoreProduct> findAll(DataTablesInput input, Predicate predicate);

    List<StoreProduct> getAllStoreProducts();

    void createStoreProduct(StoreProduct storeProduct);

}