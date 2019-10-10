package kan10.dao;

import kan10.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface LocationDAO extends JpaRepository<Location, Integer> {

    @Query("from Location where num = ?1")
    Location findByNum(int num);}
