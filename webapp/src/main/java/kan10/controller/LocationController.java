package kan10.controller;


import kan10.entities.Location;

import kan10.dao.LocationDAO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;





@RestController
public class LocationController {


    private LocationDAO locationDAO ;


    @RequestMapping(value = "/location", method = RequestMethod.POST)
    public ResponseEntity<?> createLocation(@RequestBody Location location) {
        locationDAO.save(location);

        return ResponseEntity.ok("New Location has been created succesfully");
    }

}