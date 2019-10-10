package kan10.service;

import kan10.dao.ProfileDao;
import kan10.entities.ProductCategory;
import kan10.entities.Profile;
import kan10.enums.Keywords;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ProfileServiceTest {

    @Mock
    private ProfileDao profileDao;

    @InjectMocks
    private ProfileService profileService;

    private List<ProductCategory> productCategories;

    @Before
    public void init() {

        ProductCategory productCategory1 = new ProductCategory("name1","description1");
        productCategory1.setKeywords(Keywords.getDistinct(new Random().nextInt(5)));

        ProductCategory productCategory2 = new ProductCategory("name2","description2");
        productCategory2.setKeywords(Keywords.getDistinct(new Random().nextInt(5)));

        productCategories = new ArrayList<ProductCategory>(){{
            add(productCategory1);
            add(productCategory2);
        }};

    }

    @Test
    public void testCreateProfile_OK() {

        Profile profile = new Profile(productCategories);
        List<Profile> profileList = spy(new ArrayList<>());

        when(profileDao.save(profile)).thenAnswer((i) -> {
            profile.setId(1L);
            profileList.add(profile);
            return profile;
        });

        when(profileDao.findAll()).thenReturn(profileList);

        profileService.createProfile(profile);

        List<Profile> profiles = profileService.getAllProfiles();

        assertEquals(1, profiles.size());
        Profile actual = profiles.get(0);
        assertEquals(new Long(1), actual.getId());
        assertEquals("nam_nam", actual.getLabel());
        assertEquals("name1 & name2", actual.getDescription());
        assertEquals(productCategories, actual.getProductCategories());
    }

    @Test
    public void testGetAllProfiles_OK() {

        List<Profile> profileList = new ArrayList<Profile>(){{
            add(new Profile(productCategories));
            add(new Profile(productCategories));
            add(new Profile(productCategories));
            add(new Profile(productCategories));
            add(new Profile(productCategories));
        }};

        when(profileDao.findAll()).thenReturn(profileList);

        List<Profile> profiles = profileService.getAllProfiles();

        assertEquals(5, profiles.size());

        profiles.forEach(profile -> {
            assertEquals("nam_nam", profile.getLabel());
            assertEquals("name1 & name2", profile.getDescription());
            assertEquals(productCategories, profile.getProductCategories());
        });

    }

}