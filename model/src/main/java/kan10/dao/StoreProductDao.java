package kan10.dao;

import kan10.entities.StoreProduct;
import org.springframework.data.jpa.datatables.qrepository.QDataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

public interface StoreProductDao extends JpaRepository<StoreProduct, Integer>, QuerydslPredicateExecutor<StoreProduct>, QDataTablesRepository<StoreProduct, Integer> {

StoreProduct findOneById (Long id) ;
}

