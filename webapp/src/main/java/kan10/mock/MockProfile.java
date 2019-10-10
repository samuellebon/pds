package kan10.mock;

import kan10.common.ProfileUtils;
import kan10.entities.ProductCategory;
import kan10.service.ProductCategoryServiceImpl;
import kan10.service.ProfileService;

import java.util.List;

import static kan10.common.ProfileUtils.pareto;

class MockProfile {

    private final static int numberOfProfiles = 30;
    private final static int pareto = 4;


    static void insertProfile(ProfileService profileService, ProductCategoryServiceImpl productCategoryService) {
        List<ProductCategory> productCategories = productCategoryService.getAllProductsCategories();
        for (int i = 1; i <= numberOfProfiles ; i++) profileService.createProfile(ProfileUtils.generateProfile(pareto(pareto), productCategories));
    }

}