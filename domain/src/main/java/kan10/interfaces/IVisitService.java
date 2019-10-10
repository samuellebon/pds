package kan10.interfaces;

import kan10.entities.Visit;
import java.sql.Timestamp;
import java.util.List;

/**
 * Visit service interface
 * @author suriya
 */
public interface IVisitService {

    // create visit
    boolean createVisit (Visit visit);

    // get all visit without criterias
    List<Visit> getAllVisit ();

    // stats for mall
    int getNumberAttendanceConnectedClientWithPeriod (Timestamp sstartDate, Timestamp eendDate);
    int getNumberAttendanceDiconnectedClientWithPeriod (Timestamp sstartDate, Timestamp eendDate);
    int getNumberAttendanceWithPeriod (Timestamp sstartDate, Timestamp eendDate);

    // stats for store
    int getNumberAttendanceConnectedClientWithPeriodStore(Timestamp startDate,Timestamp endDate, int id_store);
    int getNumberAttendanceDisconnectedClientWithPeriodStore(Timestamp startDate,Timestamp endDate, int id_store);
    int getNumberAttendanceWithPeriodStore(Timestamp startDate, Timestamp endDate, int id_store);
}
