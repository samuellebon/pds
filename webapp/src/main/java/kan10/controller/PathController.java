package kan10.controller;

import kan10.interfaces.StoreService;
import kan10.service.ClientService;
import kan10.service.PathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import kan10.entities.Client;
import javax.inject.Inject;

/**
 * @author IliesFaddaoui, NawelBadkouf
 * @version 1.0
 *
 * Here is the controller for path
 */
@Controller
public class PathController {

    @Autowired
    private PathService pathService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private ClientService clientService;

    List<String> selectedStore = new ArrayList<>();
    private Client selecedClient;

    @GetMapping(value="/setcustompath")
    public String setCustomPath( Model model){
        model.addAttribute("storelist", storeService.getAllStores());
        model.addAttribute("allclient", clientService.getAllClients());
        //System.out.println(String.format("Modele=%s", model));
        return "custom-path-view";
    }

    @PostMapping(value = "/setcustompath")
    public String generateCustomPath( Model model){
        Map<Integer, String> map = pathService.returnCustomerPath(selectedStore, "Store_1");
        model.addAttribute("CustomPath", map);
        System.out.println(String.format("Modele=%s", model));
        selectedStore = new ArrayList<>();
        return "generatedPath";
    }

    @PutMapping("/addSelectedStore")
    public String addStore(@RequestParam String label) {
        selectedStore.add(label);
        return "custom-path-view";
    }

    @GetMapping(value = "/pathmanage")
    public String customPathView() {return "custom-path-view"; }
    /*    @PutMapping("/removeSelectedStore")
    public String removeStore(@RequestParam String label) {
        selectedStore.removeIf(s -> s.equals(label));
        return "custom-path-view";
    }*/


    /**
     * Get all client in drop down menu
     * @param model
     * @return custom path view
     */
    @GetMapping(value="/setcustompathbyprofile")
    public String setCustomPathByProfile( Model model) {
        model.addAttribute("allclient", clientService.getAllClients());
        return "custom-path-view";
    }

    /**
     * Generate custom path thanks to id client
     * @param model
     * @return path profile client view
     */
    @PostMapping(value = "/setcustompathbyprofile")
    public String test(Model model) {
        List<String> storesByProfile = pathService.getStoresByProfile(selecedClient);
        Map<Integer, String> map = pathService.returnCustomerPath(storesByProfile, "Store_1");
        model.addAttribute("CustomPathByProfile", map);
        return "generatedPathByProfile";
    }

    /**
     * We recover the client corresponded to id client selected
     * @param id
     * @return custom path view with id client
     */
    @PutMapping("/addSelectedClient")
    public String addClient(@RequestParam Long id){
        this.selecedClient = clientService.findClientById(id).get();
        return "custom-path-view";
    }
}
