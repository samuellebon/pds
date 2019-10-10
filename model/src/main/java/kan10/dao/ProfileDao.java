package kan10.dao;

import kan10.entities.Profile;
import org.springframework.data.jpa.datatables.qrepository.QDataTablesRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileDao extends JpaRepository<Profile, Long>, QDataTablesRepository<Profile, Long> {
}
