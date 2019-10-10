package kan10.dao;

import kan10.entities.Store;
import org.springframework.data.jpa.datatables.qrepository.QDataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository


public interface StoreDAO extends JpaRepository<Store, Integer>, QuerydslPredicateExecutor<Store>, QDataTablesRepository<Store, Integer> {

    @Query("from Store where label = ?1")
    Store findByLabel(String label);
}

