package kan10.controller;

import kan10.entities.BoxDelivery;
import kan10.entities.HomeDelivery;
import kan10.entities.StoreDelivery;
import kan10.interfaces.ProductService;
import kan10.service.BoxDeliveryService;
import kan10.service.HomedeliveryService;
import kan10.service.StoreDeliveryService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * @author Ilies Faddaoui
 * @version 1.0.0
 */
@Controller
public class DeliveryController {
    HomedeliveryService homedeliveryService;
    BoxDeliveryService boxDeliveryService;
    StoreDeliveryService storeDeliveryService;
    ProductService productService;

    public DeliveryController(HomedeliveryService homedeliveryService, BoxDeliveryService boxDeliveryService, StoreDeliveryService storeDeliveryService, ProductService productService) {
        this.homedeliveryService = homedeliveryService;
        this.boxDeliveryService = boxDeliveryService;
        this.storeDeliveryService = storeDeliveryService;
        this.productService = productService;
    }

    @GetMapping(value = "/deliveriesmenu")
    public String DeliveryMenu(){
        return "deliveriesmenu";
    }

    @GetMapping(value="/createDelivery")
    public String createDelivery(){
        return "createDelivery";
    }

    @GetMapping(value="/createHomeDelivery")
    public String createHomeDelivery(Model model){
        model.addAttribute("HomeDelivery", new HomeDelivery());
        return "createHomeDelivery";
    }

    @PostMapping(value="/createHomeDelivery")
    public String createHomeDelivertSubmit(@ModelAttribute HomeDelivery homeDelivery, Model model){
        homedeliveryService.create(homeDelivery);
        return "deliveriesmenu";
    }

    @GetMapping(value="/createStoreDelivery")
    public String createStoreDelivery(Model model){
        model.addAttribute("StoreDelivery", new StoreDelivery());
        return "createStoreDelivery";
    }

    @PostMapping(value="/createStoreDelivery")
    public String createStoreDeliverySubmit(@ModelAttribute StoreDelivery storeDelivery, Model model){
        storeDeliveryService.create(storeDelivery);
        return "deliveriesmenu";
    }

    @GetMapping(value = "/createBoxDelivery")
    public String createBoxDelivery(Model model) {
        model.addAttribute("BoxDelivery", new BoxDelivery());
        return "createBoxDelivery";
    }

    @PostMapping(value="/createBoxDelivery")
    public String createBoxDeliverySubmit(@ModelAttribute BoxDelivery boxDelivery, Model model){
        boxDeliveryService.create(boxDelivery);
        return "deliveriesmenu";
    }
}
