package kan10.webapp;

import kan10.common.EnumUtils;
import kan10.common.ProfileUtils;
import kan10.entities.ProductCategory;
import kan10.entities.Profile;
import kan10.enums.Keywords;
import kan10.service.ProductCategoryServiceImpl;
import kan10.service.ProfileService;
import org.junit.Before;
import org.junit.Test;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class ProfileServiceIT extends AbstractIT {

    @Inject
    private ProfileService profileService;

    @Inject
    private ProductCategoryServiceImpl productCategoryService;

    List<ProductCategory> productCategories;

    @Before
    public void init() {

        ProductCategory productCategory1 = new ProductCategory("name1","description1");
        productCategory1.setKeywords(Keywords.getDistinct(new Random().nextInt(5)));
        productCategoryService.createProductCategory(productCategory1);

        ProductCategory productCategory2 = new ProductCategory("name2","description2");
        productCategory2.setKeywords(Keywords.getDistinct(new Random().nextInt(5)));
        productCategoryService.createProductCategory(productCategory2);

        productCategories = new ArrayList<ProductCategory>(){{
            add(productCategory1);
            add(productCategory2);
        }};

        Profile profile = new Profile(productCategories);
        profileService.createProfile(profile);

    }

    @Test
    public void testCreateProfile_OK() {
        Profile profile = new Profile(productCategories);
        profileService.createProfile(profile);

        List<Profile> profiles = profileService.getAllProfiles();
        assertEquals(2, profiles.size());
        Profile actual = profiles.get(1);
        assertEquals("nam_nam", actual.getLabel());
        assertEquals("name1 & name2", actual.getDescription());
        assertEquals(productCategories, actual.getProductCategories());
    }

    @Test
    public void testGetAllProfiles_OK() {
        List<Profile> profiles = profileService.getAllProfiles();
        assertEquals(1, profiles.size());
        Profile actual = profiles.get(0);
        assertEquals("nam_nam", actual.getLabel());
        assertEquals("name1 & name2", actual.getDescription());
        assertEquals(productCategories, actual.getProductCategories());
    }

}