package kan10.dao;

import kan10.entities.AdjacentStore;
import org.springframework.data.jpa.datatables.qrepository.QDataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface AdjacentStoreRepository extends JpaRepository<AdjacentStore, Integer>, QuerydslPredicateExecutor<AdjacentStore>, QDataTablesRepository<AdjacentStore,Integer>{
}
