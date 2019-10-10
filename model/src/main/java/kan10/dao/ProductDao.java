package kan10.dao;

import kan10.entities.Product;
import kan10.entities.StoreProduct;
import org.springframework.data.jpa.datatables.qrepository.QDataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * ProductRepository class to manage Product object from database
 * @author Suriya, Samuel
 */
@Component
@Repository
public interface ProductDao extends JpaRepository<Product, Integer>, QuerydslPredicateExecutor<Product>, QDataTablesRepository<Product, Integer> {

    @Query("from Product where name = ?1")
    Product findByName(String name);


}