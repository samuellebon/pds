package kan10.service;

import kan10.dao.VisitDao;
import kan10.entities.Visit;
import kan10.interfaces.IVisitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * Visit Service
 * @author suriya
 */
@Service
public class VisitService implements IVisitService {


    @Autowired
    private VisitDao visitDao;

    /**
     * create visit (for mock)
     * @param visit
     * @return
     */
    @Override
    public boolean createVisit (Visit visit) {
        try {
            if (visit == null) {
                return false;
            }
            else if (visit.getStoreVisit() == null) {
                return false;
            }
            else if (visit.getStartDate().after(visit.getEndDate())) {
                return false;
            }
            else {
                visitDao.save(visit);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * get all visits in the database
     * @return
     */
    @Override
    public List<Visit> getAllVisit() {
        return visitDao.findAll();
    }

    /**
     * get all visits of connected clients with period for mall
     * @param sstartDate
     * @param eendDate
     * @return
     */
    @Override
    public int getNumberAttendanceConnectedClientWithPeriod (Timestamp sstartDate, Timestamp eendDate) {
        return visitDao.getNumberAttendanceConnectedClientWithPeriod(sstartDate, eendDate);
    }

    /**
     * get all visits of disconnected clients with period for mall
     * @param sstartDate
     * @param eendDate
     * @return
     */
    @Override
    public int getNumberAttendanceDiconnectedClientWithPeriod (Timestamp sstartDate, Timestamp eendDate) {
        return visitDao.getNumberAttendanceDisconnectedClientWithPeriod(sstartDate, eendDate);
    }

    /**
     * get all visits of connected and not connected clients with period for the mall
     * @param sstartDate
     * @param eendDate
     * @return
     */
    @Override
    public int getNumberAttendanceWithPeriod (Timestamp sstartDate, Timestamp eendDate) {
        return visitDao.getNumberAttendanceWithPeriod(sstartDate, eendDate);
    }

    /**
     * get all visits of connected clients with period the store
     * @param sstartDate
     * @param eendDate
     * @param iid_store
     * @return
     */
    @Override
    public int getNumberAttendanceConnectedClientWithPeriodStore(Timestamp sstartDate,Timestamp eendDate, int iid_store) {
        return visitDao.getNumberAttendanceConnectedClientWithPeriodStore(sstartDate, eendDate, iid_store);
    }

    /**
     * get all visits of not connected clients with period the store
     * @param sstartDate
     * @param eendDate
     * @param iid_store
     * @return
     */
    @Override
    public int getNumberAttendanceDisconnectedClientWithPeriodStore(Timestamp sstartDate,Timestamp eendDate, int iid_store) {
        return visitDao.getNumberAttendanceDisconnectedClientWithPeriodStore(sstartDate, eendDate, iid_store);
    }

    /**
     * get all visits of connected and not connected clients with period for the store
     * @param sstartDate
     * @param eendDate
     * @param iid_store
     * @return
     */
    @Override
    public int getNumberAttendanceWithPeriodStore(Timestamp sstartDate, Timestamp eendDate, int iid_store) {
        return visitDao.getNumberAttendanceWithPeriodStore(sstartDate, eendDate, iid_store);
    }
}
