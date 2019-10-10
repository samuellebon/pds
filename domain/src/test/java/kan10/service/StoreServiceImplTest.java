package kan10.service;

import kan10.dao.StoreDAO;
import kan10.entities.Location;
import kan10.entities.Store;
import kan10.entities.StoreCategory;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class StoreServiceImplTest {

    @Mock
    StoreDAO storeDAO;

    @InjectMocks
    private StoreServiceImpl storeService;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    static Store store;

    static ArrayList<Store> dbstore = new ArrayList<Store>();

    static Location loc0 = new Location();
    static Location loc1 = new Location();
    static Location loc2 = new Location();
    static Location loc3 = new Location();
    static Location loc4 = new Location();
    static Location loc5 = new Location();

    static StoreCategory storeCat = new StoreCategory();

    @Before
    public void initMethod() {
        MockitoAnnotations.initMocks(this);

        dbstore.add(new Store("storesqcdsc_1", loc1, storeCat));
        dbstore.add(new Store("stovezvzere_2", loc2, storeCat));
        dbstore.add(new Store("stovzevezre_3", loc3, storeCat));
        dbstore.add(new Store("stovezvezre_4", loc4, storeCat));
        dbstore.add(new Store("storvzeve_5", loc5, storeCat));
    }

    @Test
    public void testCreateValidatorWithNullLabelShouldBeKO() {
        assertFalse(storeService.createValidator(new Store(null, loc0, storeCat)));
    }

    @Test
    public void testCreateValidatorWithNullCategoryShouldBeKO() {
        assertFalse(storeService.createValidator(new Store("store0", loc0, null)));
    }

    @Test
    public void testCreateValidatorWithNullLocationShouldBeKO() {
        assertFalse(storeService.createValidator(new Store("store0", null, storeCat)));
    }

    @Test
    public void testCreateValidatorWithEmptyLabelShouldBeKO() {
        assertFalse(storeService.createValidator(new Store("", loc0, storeCat)));
    }

    @Test
    public void testCreateValidatorWithTooLongLabelShouldBeKO() {
        assertFalse(storeService.createValidator(new Store("azertyuiopqsdfghjklmwxcvbnazertyu", loc0, storeCat)));
    }

    @Test
    public void testCreateValidatorWithOnlyMetacharacterLabelShouldBeKO() {
        assertFalse(storeService.createValidator(new Store("$€!?@", loc0, storeCat)));
    }

    @Test
    public void testCreateValidatorWithGoodValuesShouldBeOK() {
        assertTrue(storeService.createValidator(new Store("H&M", loc0, storeCat)));
    }

    @Test
    public void testWithLocationAlreadyAllocatedShouldBeOK() {
        store = new Store("name", loc1, storeCat);

        Mockito.when(storeDAO.findAll()).thenReturn(dbstore);

        assertTrue(storeService.locationAlreadyAllocated(store));
    }

    @Test
    public void testWithoutLocationAlreadyAllocatedShouldBeKO() {
        store = new Store("name", loc0, storeCat);

        Mockito.when(storeDAO.findAll()).thenReturn(dbstore);

        assertFalse(storeService.locationAlreadyAllocated(store));
    }

    @Test
    public void testCreateStoreWithBadValidatorAndBadLocationShouldBeKO() {
        store = new Store("", loc1, storeCat);

        Mockito.when(storeDAO.findAll()).thenReturn(dbstore);

        assertFalse(storeService.createStore(store));
    }

    @Test
    public void testCreateStoreWithBadValidatorAndGoodLocationShouldBeKO() {
        store = new Store("H&M", loc0, null);

        Mockito.when(storeDAO.findAll()).thenReturn(dbstore);

        assertFalse(storeService.createStore(store));
    }

    @Test
    public void testCreateStoreWithGoodValidatorAndBadLocationShouldBeKO() {
        store = new Store("H&M", loc1, storeCat);

        Mockito.when(storeDAO.findAll()).thenReturn(dbstore);

        assertFalse(storeService.createStore(store));
    }

    @Test
    public void testCreateStoreWithGoodValidatorAndGoodLocationShouldBeOK() {
        store = new Store("H&M", loc0, storeCat);

        Mockito.when(storeDAO.findAll()).thenReturn(dbstore);

        assertTrue(storeService.createStore(store));
    }
}