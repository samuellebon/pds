package kan10.api;

import kan10.entities.Campaign;
import kan10.entities.Product;
import kan10.entities.Profile;
import kan10.entities.Store;
import kan10.interfaces.ProductService;
import kan10.interfaces.StoreService;
import kan10.service.EventService;
import kan10.service.ProductServiceImpl;
import kan10.service.ProfileService;
import kan10.service.StoreServiceImpl;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CampaignApi {

    private final ProfileService profileService;
    private final ProductService productService;
    private final StoreService storeService;

    @Inject
    public CampaignApi(ProfileService profileService, ProductServiceImpl productService, StoreServiceImpl storeService, EventService eventService) {
        this.profileService = profileService;
        this.productService = productService;
        this.storeService = storeService;
    }

    @PostMapping(value = "/fetchProfile")
    public DataTablesOutput<Profile> searchProfile(@Valid @RequestBody DataTablesInput input) {
        return profileService.findAll(input);
    }

    @PostMapping(value = "/fetchProduct")
    public DataTablesOutput<Product> searchProduct(@Valid @RequestBody DataTablesInput input) {
        return productService.findAll(input);
    }

    @PostMapping(value = "/fetchStore")
    public DataTablesOutput<Store> searchStore(@Valid @RequestBody DataTablesInput input) {
        return storeService.findAll(input);
    }
}
