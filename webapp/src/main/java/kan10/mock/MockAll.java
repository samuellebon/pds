package kan10.mock;

import kan10.interfaces.LocationService;
import kan10.interfaces.StoreCategoryService;
import kan10.interfaces.StoreService;
import kan10.service.*;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import javax.inject.Inject;

@Service
public class MockAll {

    private final
    LocationService service;

    private final
    StoreCategoryService storeCategoryService;

    private final
    StoreService storeService;

    private final
    ProductCategoryServiceImpl productCategoryService;

    private final
    ProductServiceImpl productService;

    private final
    ProfileService profileService;

    private final
    StoreProductServiceImpl storeProductService;

    private final
    ClientService clientService;

    private final
    LocationService locationService;

    private final
    OrderService orderService;

    private final
    VisitService visitService;

    @Inject
    public MockAll(LocationService service, StoreCategoryService storeCategoryService, StoreService storeService, ProductCategoryServiceImpl productCategoryService, ProductServiceImpl productService, ProfileService profileService, StoreProductServiceImpl storeProductService, ClientService clientService, LocationService locationService, VisitService visitService, OrderService orderService) {
        this.service = service;
        this.storeCategoryService = storeCategoryService;
        this.storeService = storeService;
        this.productCategoryService = productCategoryService;
        this.productService = productService;
        this.profileService = profileService;
        this.storeProductService = storeProductService;
        this.clientService = clientService;
        this.locationService = locationService;
        this.orderService = orderService;
        this.visitService = visitService;
    }

    @PostConstruct
    public void init() {
        MockProductCategory.insertProductCategories(productCategoryService);
        MockData.mockStore(storeService, storeCategoryService, locationService, productCategoryService);
        MockProfile.insertProfile(profileService,productCategoryService);
        MockClient.insertClient(profileService,clientService);
        MockOrder.insertAll(productService,productCategoryService,clientService,orderService,storeProductService,storeService);
        MockData.mockVisitWithOutliers(clientService,storeService,visitService);
        //MockData.mockVisit(clientService,storeCategoryService,storeService,visitService);
        //MockProduct.insertStoreProduct( storeProductService,  storeService,  productService);

    }

}