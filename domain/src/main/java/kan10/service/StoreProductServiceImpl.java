package kan10.service;

import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.BooleanExpression;
import kan10.dao.StoreProductDao;
import kan10.entities.StoreProduct;
import kan10.interfaces.StoreProductService;
import kan10.predicate.StoreProductPredicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreProductServiceImpl implements StoreProductService {

    @Autowired
    StoreProductDao storeProductDao;

    @Override
    public BooleanExpression hasName(String name) {
        return StoreProductPredicate.hasName(name);
    }

    @Override
    public BooleanExpression hasCategory(String category) {
        return StoreProductPredicate.hasCategory(category);
    }

    @Override
    public BooleanExpression hasStore(String store) {
        return StoreProductPredicate.hasStore(store);
    }

    @Override
    public DataTablesOutput<StoreProduct> findAll(DataTablesInput input, Predicate predicate) {
        return storeProductDao.findAll(input, predicate);
    }

    @Override
    public List<StoreProduct> getAllStoreProducts() {
        return storeProductDao.findAll();
    }

    @Override
    public void createStoreProduct(StoreProduct storeProduct) {
        storeProductDao.save(storeProduct);
    }


    public StoreProduct findByID (Long id){
        return storeProductDao.findOneById( id ) ;

    }

}