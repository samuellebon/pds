package kan10.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import kan10.dao.StoreDAO;
import kan10.entities.Store;
import kan10.interfaces.LocationService;
import kan10.service.StoreCategoryServiceImpl;
import kan10.service.StoreServiceImpl;


@Controller
public class StoreController {

    @Autowired
    private StoreDAO storeDAO;

    @Autowired
    private StoreServiceImpl storeService;

    @Autowired
    private StoreCategoryServiceImpl storeCategoryService;

    @Autowired
    LocationService locationService;

    @GetMapping("/createstore")
    public String createStore(Model model) {
        model.addAttribute("store", new Store());
        model.addAttribute("categories", storeCategoryService.getAllStoreCategories());
        model.addAttribute("locations", locationService.getAllLocations());
        return "createStore";
    }

    @GetMapping("/searchstore")
    public String searchStore(Model model) {
        model.addAttribute("stores", storeService.getAllStores());
        model.addAttribute("categories", storeCategoryService.getAllStoreCategories());
        model.addAttribute("locations", locationService.getAllLocations());
        return "searchStore";
    }
}


