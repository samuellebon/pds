package kan10.controller;


import kan10.dao.StoreCategoryDAO;
import kan10.entities.StoreCategory;



import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;




@RestController
public class StoreCategoryController {


    private StoreCategoryDAO storeCategoryDAO ;


    @RequestMapping(value = "/storeCategory", method = RequestMethod.POST)
    public ResponseEntity<?> createStoreCategory(@RequestBody StoreCategory storeCategory) {
        storeCategoryDAO.save(storeCategory);

        return ResponseEntity.ok("New StoreCategory has been created succesfully");
    }

}