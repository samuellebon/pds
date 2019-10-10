package kan10.dao;

import kan10.entities.StoreCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface StoreCategoryDAO extends JpaRepository<StoreCategory, Integer> {

    @Query("from StoreCategory where name = ?1")
    StoreCategory findByName(String name);}
