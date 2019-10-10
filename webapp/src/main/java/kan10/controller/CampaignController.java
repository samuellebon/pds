package kan10.controller;

import kan10.entities.*;
import kan10.service.*;
import org.hibernate.mapping.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class CampaignController {

    static List<Profile> profiles;
    static List<Product> products;
    static List<Store> stores;
    static List<Event> events;

    List<Profile> selectedProfiles = new ArrayList<>();
    List<Product> selectedProducts = new ArrayList<>();
    List<Store> selectedStores = new ArrayList<>();
    Event selectedEvent = new Event();

    private static ProfileService profileService;

    private static CampaignService campaignService;

    private static ProductServiceImpl productService;

    private static StoreServiceImpl storeService;

    @Autowired
    private static EventService eventService;

    @Inject
    public CampaignController(ProfileService profileService, ProductServiceImpl productService, StoreServiceImpl storeService, EventService eventService, CampaignService campaignService) {
        this.profileService = profileService;
        this.productService = productService;
        this.storeService = storeService;
        this.eventService = eventService;
        this.campaignService = campaignService;
    }

    @GetMapping(value = "/eventsmanage")
    public String eventsManage(){
        return "eventsManage";
    }

    @GetMapping("/events")
    public String event() {
        return "Event";
    }

    @GetMapping("/createCampaign")
    public String createCampaign(Model model) {
        this.profiles = profileService.getAllProfiles();
        this.products = productService.getAllProducts();
        this.stores = storeService.getAllStores();
        this.events = eventService.getAllEvents();
        model.addAttribute("events", eventService.getAllEvents());

        return "createCampaign";
    }

    private void addEvent (int id) {
        Optional<Event> e = events.stream().filter(event -> event.getId() == id).findFirst();

        e.ifPresent(event -> selectedEvent = event);
    }

    private void addProfile (int id) {
        Optional<Profile> p = profiles.stream().filter(profile -> profile.getId() == id).findFirst();

        p.ifPresent(profile -> selectedProfiles.add(profile));
    }

    private void addProduct (int id) {
        Optional<Product> p = products.stream().filter(product -> product.getId() == id).findFirst();

        p.ifPresent(product -> selectedProducts.add(product));
    }

    private void addStore (int id) {
        Optional<Store> s = stores.stream().filter(store -> store.getId() == id).findFirst();

        s.ifPresent(store -> selectedStores.add(store)); // idem : if (s.isPresent()) selectedStores.add(s.get());
    }

    private void removeProfile (int id) {
        selectedProfiles.removeIf(profile -> profile.getId() == id);
    }

    private void removeProduct (int id) {
        selectedProfiles.removeIf(product -> product.getId() == id);
    }

    private void removeStore (int id) {
        selectedProfiles.removeIf(store -> store.getId() == id);
    }

    @PutMapping("/addProfile")
    public String addProfileApi(@RequestParam int id) {
        addProfile(id);
        return "createCampaign::#profilTable";
    }

    @PutMapping("/addEvent")
    public String addEventApi(@RequestParam int id) {
        addEvent(id);
        return "createCampaign::#form";
    }

    @PutMapping("/addProduct")
    public String addProductApi(@RequestParam int id) {
        addProduct(id);
        return "createCampaign::#productTable";
    }

    @PutMapping("/addStore")
    public String addStoreApi(@RequestParam int id) {
        addStore(id);
        return "createCampaign::#storeTable";
    }

    @PutMapping("/removeProfile")
    public String removeProfileApi(@RequestParam int id) {
        removeProfile(id);
        return "createCampaign::#profilTable";
    }

    @PutMapping("/removeProduct")
    public String removeProductApi(@RequestParam int id) {
        removeProduct(id);
        return "createCampaign::#productTable";
    }

    @PutMapping("/removeStore")
    public String removeStoreApi(@RequestParam int id) {
        removeStore(id);
        return "createCampaign::#storeTable";
    }

    @PutMapping("/resetCampaign")
    public String resetCampaign() {
        selectedProfiles.clear();
        selectedProducts.clear();
        selectedStores.clear();
        selectedEvent = new Event();

        return "createCampaign::#form";
    }

    @PostMapping("/createCampaign")
    public String campaignCreation() {
        Campaign c = new Campaign();
        c.setProfiles(selectedProfiles);
        c.setProducts(selectedProducts);
        c.setStores(selectedStores);
        c.setEvent(selectedEvent);

        campaignService.createCampaign(c);

        selectedProfiles.clear();
        selectedProducts.clear();
        selectedStores.clear();
        selectedEvent = new Event();

        return "createCampaign::#storeTable";
    }
}
