package kan10.dao;

import kan10.entities.Campaign;
import kan10.entities.Event;
import kan10.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CampaignDao extends JpaRepository<Campaign, Long> {

    Campaign findOneByEvent(Event event);

}
