package kan10.dao;

import kan10.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EventDao extends JpaRepository<Event, Long> {

    Event findOneByLabel(String label);

}
