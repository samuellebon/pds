package kan10.mock;

import kan10.common.RandomDates;
import kan10.entities.*;
import kan10.interfaces.LocationService;
import kan10.interfaces.StoreCategoryService;
import kan10.interfaces.StoreService;
import kan10.service.ClientService;
import kan10.service.ProductCategoryServiceImpl;
import kan10.service.VisitService;
import java.sql.Timestamp;
import java.util.*;
import java.util.stream.Collectors;


class MockData {

    static void mockStore(StoreService storeService, StoreCategoryService storeCategoryService, LocationService locationService, ProductCategoryServiceImpl productCategoryService) {

        Set<ProductCategory> productCategories = new HashSet<>(productCategoryService.getAllProductsCategories());

        for (int i = 1; i <= 150; i++) {
            // Entity
            Location location = new Location(1, "left", i, i);
            locationService.saveLocationToDb(location);
            StoreCategory storeCategory = new StoreCategory("StoreCategory_" + i, "StoreCategory_Description" + i);
            storeCategoryService.saveStoreCategoryToDb(storeCategory);
            Store store = new Store("Store_" + i, location, storeCategory, getDistinct(new Random().nextInt(5) + 1, productCategories));
            storeService.saveStoreToDb(store);
        }
    }

    private static Set<ProductCategory> getDistinct(int n, Set<ProductCategory> productCategories ) {
        List<ProductCategory> productCategoryList = new ArrayList<>(productCategories);
        Collections.shuffle(productCategoryList);
        return productCategoryList.stream().limit(n).collect(Collectors.toSet());
    }

    public static void mockVisit (ClientService clientService, StoreService storeService, VisitService visitService) {

        List<Client> clients = clientService.getAllClients();
        List<Store> stores = storeService.getAllStores();
        Client client;
        Store store;
        Visit visit = null;

        for (int i = 1; i <= 400; i++) {
            client = clients.get(new Random().nextInt(clients.size()));
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDate();
            Timestamp date2 = RandomDates.RandomTimeStampDate();

            if ( date1.before(date2)){
                visit = new Visit(store, client, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, client, date2, date1);
            }
            visitService.createVisit(visit);
        }

        for (int i = 1; i <= 600; i++) {
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDate();
            Timestamp date2 = RandomDates.RandomTimeStampDate();

            if ( date1.before(date2)){
                visit = new Visit(store, null, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, null, date2, date1);
            }
            visitService.createVisit(visit);
        }
    }

    /**
     * Mock Visit with Outliers
     * We take into consideration connected clients and disconnected client for each month
     * @param clientService
     * @param storeService
     * @param visitService
     */
    public static void mockVisitWithOutliers (ClientService clientService, StoreService storeService, VisitService visitService)
    {
        List<Client> clients = clientService.getAllClients();
        List<Store> stores = storeService.getAllStores();
        Client client;
        Store store;
        Visit visit = null;

        // january month 2018 with connected client
        for (int i=1; i <= 303; i++) {
            client = clients.get(new Random().nextInt(clients.size()));
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateJanuary();
            Timestamp date2 = RandomDates.RandomTimeStampDateJanuary();
            if (date1.before(date2)){
                visit = new Visit(store, client, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, client, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // february month 2018 with connected client
        for (int i=1; i <= 250; i++) {
            client = clients.get(new Random().nextInt(clients.size()));
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateFebruary();
            Timestamp date2 = RandomDates.RandomTimeStampDateFebruary();
            if (date1.before(date2)){
                visit = new Visit(store, client, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, client, date2, date1);
            }
            visitService.createVisit(visit);
        }


        // march month 2018 with connected client
        for (int i=1; i <= 237; i++) {
            client = clients.get(new Random().nextInt(clients.size()));
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateMarch();
            Timestamp date2 = RandomDates.RandomTimeStampDateMarch();
            if (date1.before(date2)){
                visit = new Visit(store, client, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, client, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // april month 2018 with connected client
        for (int i=1; i <= 243; i++) {
            client = clients.get(new Random().nextInt(clients.size()));
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateApril();
            Timestamp date2 = RandomDates.RandomTimeStampDateApril();
            if (date1.before(date2)){
                visit = new Visit(store, client, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, client, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // may month 2018 with connected client
        for (int i=1; i <= 198; i++) {
            client = clients.get(new Random().nextInt(clients.size()));
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateMay();
            Timestamp date2 = RandomDates.RandomTimeStampDateMay();
            if (date1.before(date2)){
                visit = new Visit(store, client, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, client, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // june month 2018 with connected client
        for (int i=1; i <= 237; i++) {
            client = clients.get(new Random().nextInt(clients.size()));
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateJune();
            Timestamp date2 = RandomDates.RandomTimeStampDateJune();
            if (date1.before(date2)){
                visit = new Visit(store, client, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, client, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // july month 2018 with connected client
        for (int i=1; i <= 250; i++) {
            client = clients.get(new Random().nextInt(clients.size()));
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateJuly();
            Timestamp date2 = RandomDates.RandomTimeStampDateJuly();
            if (date1.before(date2)){
                visit = new Visit(store, client, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, client, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // august month 2018 with connected client
        for (int i=1; i <= 304; i++) {
            client = clients.get(new Random().nextInt(clients.size()));
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateAugust();
            Timestamp date2 = RandomDates.RandomTimeStampDateAugust();
            if (date1.before(date2)){
                visit = new Visit(store, client, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, client, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // september month 2018 with connected client
        for (int i=1; i <= 430; i++) {
            client = clients.get(new Random().nextInt(clients.size()));
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateSeptember();
            Timestamp date2 = RandomDates.RandomTimeStampDateSeptember();
            if (date1.before(date2)){
                visit = new Visit(store, client, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, client, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // october month 2018 with connected client
        for (int i=1; i <= 101; i++) {
            client = clients.get(new Random().nextInt(clients.size()));
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateOctober();
            Timestamp date2 = RandomDates.RandomTimeStampDateOctober();
            if (date1.before(date2)){
                visit = new Visit(store, client, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, client, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // november month 2018 with connected client
        for (int i=1; i <= 117; i++) {
            client = clients.get(new Random().nextInt(clients.size()));
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateNovember();
            Timestamp date2 = RandomDates.RandomTimeStampDateNovember();
            if (date1.before(date2)){
                visit = new Visit(store, client, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, client, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // december month 2018 with connected client
        for (int i=1; i <= 455; i++) {
            client = clients.get(new Random().nextInt(clients.size()));
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateDecember();
            Timestamp date2 = RandomDates.RandomTimeStampDateDecember();
            if (date1.before(date2)){
                visit = new Visit(store, client, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, client, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // disconnected client 2018
        for (int i = 1; i <= 930; i++) {
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDate();
            Timestamp date2 = RandomDates.RandomTimeStampDate();
            if (date1.before(date2)){
                visit = new Visit(store, null, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, null, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // february month 2018 with disconnected client
        for (int i = 1; i <= 279; i++) {
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateFebruary();
            Timestamp date2 = RandomDates.RandomTimeStampDateFebruary();
            if (date1.before(date2)){
                visit = new Visit(store, null, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, null, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // february month 2018 with disconnected client
        for (int i = 1; i <= 279; i++) {
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateFebruary();
            Timestamp date2 = RandomDates.RandomTimeStampDateFebruary();
            if (date1.before(date2)){
                visit = new Visit(store, null, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, null, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // september month 2018 with disconnected client
        for (int i = 1; i <= 239; i++) {
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateSeptember();
            Timestamp date2 = RandomDates.RandomTimeStampDateSeptember();
            if (date1.before(date2)){
                visit = new Visit(store, null, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, null, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // december month 2018 with disconnected client
        for (int i = 1; i <= 540; i++) {
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateDecember();
            Timestamp date2 = RandomDates.RandomTimeStampDateDecember();
            if (date1.before(date2)){
                visit = new Visit(store, null, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, null, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // disconnected client 2017
        for (int i = 1; i <= 1089; i++) {
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateLastYear();
            Timestamp date2 = RandomDates.RandomTimeStampDateLastYear();
            if (date1.before(date2)){
                visit = new Visit(store, null, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, null, date2, date1);
            }
            visitService.createVisit(visit);
        }

        // december month 2018 with connected client
        for (int i=1; i <= 455; i++) {
            client = clients.get(new Random().nextInt(clients.size()));
            store = stores.get(new Random().nextInt(stores.size()));
            Timestamp date1 = RandomDates.RandomTimeStampDateLastYear();
            Timestamp date2 = RandomDates.RandomTimeStampDateLastYear();
            if (date1.before(date2)){
                visit = new Visit(store, client, date1, date2);
            }
            if (date1.after(date2)){
                visit = new Visit(store, client, date2, date1);
            }
            visitService.createVisit(visit);
        }

    }

}