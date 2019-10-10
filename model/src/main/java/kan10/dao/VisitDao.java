package kan10.dao;

import kan10.entities.Visit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import java.sql.Timestamp;

/**
 * Visit Repository
 * @author suriya
 */
@Component
@Repository
public interface VisitDao extends JpaRepository<Visit, Integer> {

    // count attendance (nb visit) of clients connected by month and/or year
    @Query(value = "select count (id) from visit where id_client is not null and startDate between :sstartDate and :eendDate", nativeQuery = true)
    int getNumberAttendanceConnectedClientWithPeriod(@Param("sstartDate") Timestamp sstartDate, @Param("eendDate") Timestamp eendDate);

    // count attendance (nb visit) of clients not connected by month and/or year
    @Query(value = "select count (id) from visit where id_client is null and startDate between :startDate and :endDate", nativeQuery = true)
    int getNumberAttendanceDisconnectedClientWithPeriod(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

    // count attendance of clients connected and not connected by month and/or year
    @Query(value = "select count (visit.id) from visit where startDate between :startDate and :endDate", nativeQuery = true)
    int getNumberAttendanceWithPeriod(@Param("startDate") Timestamp startDate, @Param("endDate") Timestamp endDate);

    // count attendance of clients not connected by year and/or month from a particular store
    @Query(value = "select count (visit.id) from visit, store where visit.id_store = store.id and id_client is not null startDate between :sstartDate and :eendDate and id_store = :iid_store group by store.label", nativeQuery = true)
    int getNumberAttendanceConnectedClientWithPeriodStore(@Param("sstartDate") Timestamp startDate, @Param("eendDate") Timestamp endDate, @Param("iid_store") int id_store);

    // count attendance of clients connected by year and/or month from a particular store
    @Query(value = "select count (visit.id) from visit, store where visit.id_store = store.id and id_client is null and startDate between :sstartDate and :eendDate and id_store = :iid_store group by store.label", nativeQuery = true)
    int getNumberAttendanceDisconnectedClientWithPeriodStore(@Param("sstartDate") Timestamp startDate, @Param("eendDate") Timestamp endDate, @Param("iid_store") int id_store);

    // count attendance of all clients by year and/or month from a particular store
    @Query(value = "select count (visit.id) from visit, store where visit.id_store = store.id and startDate between :sstartDate and :eendDate and id_store = :iid_store group by store.label", nativeQuery = true)
    int getNumberAttendanceWithPeriodStore(@Param("sstartDate") Timestamp startDate, @Param("eendDate") Timestamp endDate, @Param("iid_store") int id_store);

}

