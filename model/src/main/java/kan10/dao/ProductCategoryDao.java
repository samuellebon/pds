package kan10.dao;

import kan10.entities.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

/**
 * ProductCategoryRepository class to manage ProductCategory object from database
 * @author Suriya, Samuel
 */
@Component
@Repository
public interface ProductCategoryDao extends JpaRepository<ProductCategory, Integer> {

    @Query("from ProductCategory where name = ?1")
    ProductCategory findByName(String name);
}
