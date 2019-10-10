package kan10.dao;

import kan10.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Component
@Repository
public interface ClientDao extends JpaRepository<Client, Long> {

}
