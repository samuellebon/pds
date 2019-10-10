package kan10.interfaces;

import kan10.entities.Location;

import java.util.List;

public interface LocationService {


    List <Location>  getAllLocations();

    Location saveLocationToDb(Location location);

}
