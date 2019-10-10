package kan10.service;



import kan10.dao.LocationDAO;

import kan10.entities.Location;

import kan10.interfaces.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;



@Service
public class LocationServiceImpl implements LocationService {

    @Autowired

    private LocationDAO locationDAO;

    @Override
    public Location saveLocationToDb(Location location) {
        return locationDAO.save(location);
    }

    @Override
    public List<Location> getAllLocations() {
        return locationDAO.findAll();
    }
}