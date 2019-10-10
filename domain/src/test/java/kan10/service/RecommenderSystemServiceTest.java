package kan10.service;

import kan10.common.EnumUtils;
import kan10.common.ProductCategoryTitle;
import kan10.entities.*;
import kan10.enums.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toMap;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class RecommenderSystemServiceTest {

    @InjectMocks
    RecommenderSystemService service;

    private ProductCategory art;
    private ProductCategory aut;
    private ProductCategory bab;
    private ProductCategory clo;
    private ProductCategory ele;
    private ProductCategory foo;
    private ProductCategory hom;
    private ProductCategory mov;
    private ProductCategory pha;
    private ProductCategory spo;
    private ProductCategory toy;

    private Product product1;
    private Product product2;
    private Product product3;
    private Product product4;
    private Product product5;
    private Product product6;
    private Product product7;
    private Product product8;
    private Product product9;
    private Product product10;

    private StoreProduct sp1;
    private StoreProduct sp2;
    private StoreProduct sp3;
    private StoreProduct sp5;
    private StoreProduct sp6;
    private StoreProduct sp7;
    private StoreProduct sp8;
    private StoreProduct sp9;
    private StoreProduct sp11;
    private StoreProduct sp13;
    private StoreProduct sp15;
    private StoreProduct sp16;
    private StoreProduct sp17;
    private StoreProduct sp19;
    private StoreProduct sp20;
    private StoreProduct sp21;

    private Profile art_aut_bab;

    private Client client1;

    private List<Client> clients;
    private List<Product> products;
    private List<OrderLine> orderLines;
    private List<ProductCategory> productCategories;

    @Before
    public void init() {

        art = new ProductCategory();
        aut = new ProductCategory();
        bab = new ProductCategory();
        clo = new ProductCategory();
        ele = new ProductCategory();
        foo = new ProductCategory();
        hom = new ProductCategory();
        mov = new ProductCategory();
        pha = new ProductCategory();
        spo = new ProductCategory();
        toy = new ProductCategory();

        art.setName(ProductCategoryTitle.ART_CRAFT_SEWING_PARTY_SUPPLIES);
        art.setDescription("");
        art.setKeywords(EnumUtils.getAll(ArtCraftSewingPartySuppliesCategory.class));

        aut.setName(ProductCategoryTitle.AUTO_TIRES_INDUSTRIAL);
        aut.setDescription("");
        aut.setKeywords(EnumUtils.getAll(AutoTiresIndustrialCategory.class));

        bab.setName(ProductCategoryTitle.BABY);
        bab.setDescription("");
        bab.setKeywords(EnumUtils.getAll(BabyCategory.class));

        clo.setName(ProductCategoryTitle.CLOTHING_SHOES_ACCESSORIES);
        clo.setDescription("");
        clo.setKeywords(EnumUtils.getAll(ClothingShoesAccessoriesCategory.class));

        ele.setName(ProductCategoryTitle.ELECTRONICS_OFFICE);
        ele.setDescription("");
        ele.setKeywords(EnumUtils.getAll(ElectronicOfficeCategory.class));

        foo.setName(ProductCategoryTitle.FOOD_HOUSEHOLD_GETS);
        foo.setDescription("");
        foo.setKeywords(EnumUtils.getAll(FoodHouseholdGetsCategory.class));

        hom.setName(ProductCategoryTitle.HOME_FURNITURE_APPLIANCES);
        hom.setDescription("");
        hom.setKeywords(EnumUtils.getAll(HomeFurnitureApplicancesCategory.class));

        mov.setName(ProductCategoryTitle.MOVIES_VIDEO_BOOKS);
        mov.setDescription("");
        mov.setKeywords(EnumUtils.getAll(MoviesVideoBooksCategory.class));

        pha.setName(ProductCategoryTitle.PHARMACY_HEALTH_BEAUTY);
        pha.setDescription("");
        pha.setKeywords(EnumUtils.getAll(PharmacyHealthBeautyCategory.class));

        spo.setName(ProductCategoryTitle.SPORT_FITNESS_OUTDOORS);
        spo.setDescription("");
        spo.setKeywords(EnumUtils.getAll(SportFitnessOutdoorsCategory.class));

        toy.setName(ProductCategoryTitle.TOYS_VIDEO_GAMES);
        toy.setDescription("");
        toy.setKeywords(EnumUtils.getAll(ToysVideoGamesCategory.class));

        productCategories = new ArrayList<>(Arrays.asList(art, aut, bab, clo, ele, foo, hom, mov, pha, spo, toy));

        product1 = new Product("name_1", art, art.getKeywords().get(0));
        product2 = new Product("name_2", art, art.getKeywords().get(1));
        product3 = new Product("name_3", aut, aut.getKeywords().get(0));
        product4 = new Product("name_4", aut, aut.getKeywords().get(1));
        product5 = new Product("name_5", bab, bab.getKeywords().get(0));
        product6 = new Product("name_6", bab, bab.getKeywords().get(1));
        product7 = new Product("name_7", clo, clo.getKeywords().get(0));
        product8 = new Product("name_8", clo, clo.getKeywords().get(1));
        product9 = new Product("name_9", ele, ele.getKeywords().get(0));
        product10 = new Product("name_10", ele, ele.getKeywords().get(1));
        Product product11 = new Product("name_11", foo, foo.getKeywords().get(0));
        Product product12 = new Product("name_12", foo, foo.getKeywords().get(1));
        Product product13 = new Product("name_13", hom, hom.getKeywords().get(0));
        Product product14 = new Product("name_14", hom, hom.getKeywords().get(1));
        Product product15 = new Product("name_15", mov, mov.getKeywords().get(0));
        Product product16 = new Product("name_16", mov, mov.getKeywords().get(1));
        Product product17 = new Product("name_17", pha, pha.getKeywords().get(0));
        Product product18 = new Product("name_18", pha, pha.getKeywords().get(1));
        Product product19 = new Product("name_19", spo, spo.getKeywords().get(0));
        Product product20 = new Product("name_20", spo, spo.getKeywords().get(1));
        Product product21 = new Product("name_21", toy, toy.getKeywords().get(0));
        Product product22 = new Product("name_22", toy, toy.getKeywords().get(1));

        products = new ArrayList<>(Arrays.asList(
                product1, product2, product3, product4, product5,
                product6, product7, product8, product9, product10,
                product11, product12, product13, product14, product15,
                product16, product17, product18, product19, product20,
                product21, product22));

        Location location = new Location(0, "aisle", 0, 0);
        StoreCategory storeCategory = new StoreCategory("name", "description");
        Store store = new Store("label", location, storeCategory, new HashSet<>());

        sp1 = new StoreProduct(product1, store, 0,0);
        sp2 = new StoreProduct(product2, store, 0,0);
        sp3 = new StoreProduct(product3, store, 0,0);
        sp5 = new StoreProduct(product5, store, 0,0);
        sp6 = new StoreProduct(product6, store, 0,0);
        sp7 = new StoreProduct(product7, store, 0,0);
        sp8 = new StoreProduct(product8, store, 0,0);
        sp9 = new StoreProduct(product9, store, 0,0);
        sp11 = new StoreProduct(product11, store, 0,0);
        sp13 = new StoreProduct(product13, store, 0,0);
        sp15 = new StoreProduct(product15, store, 0,0);
        sp16 = new StoreProduct(product16, store, 0,0);
        sp17 = new StoreProduct(product17, store, 0,0);
        sp19 = new StoreProduct(product19, store, 0,0);
        sp20 = new StoreProduct(product20, store, 0,0);
        sp21 = new StoreProduct(product21, store, 0,0);

        art_aut_bab = new Profile(asList(art, aut, bab));
        Profile clo_ele_foo = new Profile(asList(clo, ele, foo));
        Profile hom_mov_pha = new Profile(asList(hom, mov, pha));
        Profile spo_toy = new Profile(asList(spo, toy));
        Profile art_mov_spo = new Profile(asList(art, mov, spo));

        client1 = new Client(1, "email1", "phone");
        Client client2 = new Client(1, "email2", "phone");
        Client client3 = new Client(1, "email3", "phone");
        Client client4 = new Client(1, "email4", "phone");
        Client client5 = new Client(1, "email5", "phone");

        client1.setMajor(art_aut_bab);
        client1.setMinor(spo_toy);

        Order order1 = new Order();
        order1.setOrderLines(new ArrayList<OrderLine>(){{
            add(new OrderLine(sp1, 3));
            add(new OrderLine(sp3, 2));
            add(new OrderLine(sp5, 2));
        }});
        client1.setOrders(new HashSet<Order>(){{ add(order1);}});


        client2.setMajor(clo_ele_foo);
        client2.setMinor(spo_toy);

        Order order2 = new Order();
        order2.setOrderLines(new ArrayList<OrderLine>(){{
            add(new OrderLine(sp7, 2));
            add(new OrderLine(sp9, 2));
            add(new OrderLine(sp11, 2));
        }});
        client2.setOrders(new HashSet<Order>(){{ add(order2);}});

        client3.setMajor(hom_mov_pha);
        client3.setMinor(art_aut_bab);

        Order order3= new Order();
        order3.setOrderLines(new ArrayList<OrderLine>(){{
            add(new OrderLine(sp13, 2));
            add(new OrderLine(sp15, 2));
            add(new OrderLine(sp17, 2));
        }});
        client3.setOrders(new HashSet<Order>(){{ add(order3);}});

        client4.setMajor(art_mov_spo);
        client4.setMinor(clo_ele_foo);

        Order order4 = new Order();
        order4.setOrderLines(new ArrayList<OrderLine>(){{
            add(new OrderLine(sp2, 2));
            add(new OrderLine(sp16, 2));
            add(new OrderLine(sp19, 2));
        }});
        client4.setOrders(new HashSet<Order>(){{ add(order4);}});

        client5.setMajor(spo_toy);
        client5.setMinor(hom_mov_pha);

        Order order5= new Order();
        order5.setOrderLines(new ArrayList<OrderLine>(){{
            add(new OrderLine(sp20, 2));
            add(new OrderLine(sp21, 2));
        }});
        client5.setOrders(new HashSet<Order>(){{ add(order5);}});

        clients = new ArrayList<>(asList(client1, client2, client3, client4, client5));
        orderLines = new ArrayList<>(Arrays.asList(
                order1.getOrderLines().get(0),
                order2.getOrderLines().get(0),
                order3.getOrderLines().get(0),
                order4.getOrderLines().get(0),
                order5.getOrderLines().get(0)
        ));

    }

    @Test
    public void mapProductWithOrderCount() {

        HashMap<Product, Integer> map = service.mapProductWithOrderCount(products, orderLines);

        assertNotNull(map);

        List<Product> productPurchased = orderLines.stream().map(o -> o.getStoreProduct().getProduct()).collect(Collectors.toList());

        // Assert that purchased product appear in orders
        productPurchased.forEach(p -> assertTrue(map.get(p) > 0));

        // Get the non purchased product
        products.removeAll(productPurchased);

        // Assert that they do not appear in order count
        products.forEach(p -> assertEquals(0, (int) map.get(p)));

        // Ensure the max order count is 3 (product1)
        Integer max = Collections.max(new ArrayList<>(map.values()));
        assertEquals(3, (int) max);

    }

    @Test
    public void mapCategoryWithProduct() {

        HashMap<Product, Integer> productMapOrderCount = new HashMap<>();
        products.forEach(p -> productMapOrderCount.put(p, 0));

        HashMap<String, LinkedHashSet<Product>> map = service.mapCategoryWithProduct(productCategories, productMapOrderCount);

        // Here we know that each category has 2 product
        map.forEach((key, value) -> assertEquals(2, value.size()));
    }

    @Test
    public void mapCategoryWithPopularity() {
        HashMap<String, LinkedHashSet<Product>> categoryMapProduct = productCategories.stream().collect(toMap(ProductCategory::getName, category -> new LinkedHashSet<>(), (a, b) -> b, HashMap::new));

        products.forEach(product -> {
            ProductCategory category = product.getProductCategory();
            LinkedHashSet<Product> products = categoryMapProduct.get(category.getName());
            products.add(product);
            categoryMapProduct.put(category.getName(), products);
        });


        HashMap<Product, Integer> productMapOrderCount = service.mapProductWithOrderCount(products, orderLines);

        HashMap<String, Integer> map = service.mapCategoryWithPopularity(categoryMapProduct, productMapOrderCount);

        assertEquals(5, (int) map.get(art.getName()));
        assertEquals(0, (int) map.get(aut.getName()));
        assertEquals(0, (int) map.get(bab.getName()));
        assertEquals(2, (int) map.get(clo.getName()));
        assertEquals(0, (int) map.get(ele.getName()));
        assertEquals(0, (int) map.get(foo.getName()));
        assertEquals(2, (int) map.get(hom.getName()));
        assertEquals(0, (int) map.get(mov.getName()));
        assertEquals(0, (int) map.get(pha.getName()));
        assertEquals(2, (int) map.get(spo.getName()));
        assertEquals(0, (int) map.get(toy.getName()));

    }

    @Test
    public void sortProductsByPopularity() {

        HashMap<Product, Integer> map = new HashMap<Product,Integer>(){{
            put(product1, 1);
            put(product2, 3);
            put(product3, 14);
            put(product4, 4);
            put(product5, 6);
            put(product6, 142);
            put(product7, 23);
            put(product8, 6);
            put(product9, 2);
            put(product10, 19);
        }};

        map = service.sortProductsByPopularity(map);

        List<Integer> list = new ArrayList<>(map.values());

        Integer c = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            assertTrue(c >= list.get(i));
            c = list.get(i);
        }
    }

    @Test
    public void sortCategoryByPopularity() {
        HashMap<String, Integer> map = new HashMap<String,Integer>(){{
            put(art.getName(), 1);
            put(aut.getName(), 3);
            put(bab.getName(), 14);
            put(clo.getName(), 4);
            put(ele.getName(), 6);
            put(foo.getName(), 142);
            put(hom.getName(), 23);
            put(mov.getName(), 6);
            put(pha.getName(), 2);
            put(spo.getName(), 19);
            put(toy.getName(), 19);
        }};

        map = service.sortCategoryByPopularity(map);

        List<Integer> list = new ArrayList<>(map.values());

        Integer c = list.get(0);
        for (int i = 1; i < list.size(); i++) {
            assertTrue(c >= list.get(i));
            c = list.get(i);
        }
    }

    @Test
    public void mapProductAndCategoryPurchased() {

        HashMap<String, HashSet<Product>> clientMapProductPurchased = new HashMap<>();
        HashMap<String, HashSet<ProductCategory>> clientMapCategoryPurchased = new HashMap<>();

        service.mapProductAndCategoryPurchased(client1, clientMapProductPurchased, clientMapCategoryPurchased);

        HashSet<Product> productsPurchased = clientMapProductPurchased.get(client1.getEmail());
        HashSet<ProductCategory> categoriesPurchased = clientMapCategoryPurchased.get(client1.getEmail());

        assertEquals(3, productsPurchased.size());
        assertTrue(productsPurchased.contains(product1));
        assertTrue(productsPurchased.contains(product3));
        assertTrue(productsPurchased.contains(product5));

        assertEquals(3, categoriesPurchased.size());
        assertTrue(categoriesPurchased.contains(art));
        assertTrue(categoriesPurchased.contains(aut));
        assertTrue(categoriesPurchased.contains(bab));
    }

    @Test
    public void mapClientsProductsAndCategoriesPurchased() {
        HashMap<String, HashSet<Product>> clientMapProductPurchased = new HashMap<>();
        HashMap<String, HashSet<ProductCategory>> clientMapCategoryPurchased = new HashMap<>();

        service.mapClientsProductsAndCategoriesPurchased(clients, clientMapProductPurchased, clientMapCategoryPurchased);


        clients.forEach(client -> {
            HashSet<Product> productsPurchased = clientMapProductPurchased.get(client1.getEmail());
            HashSet<ProductCategory> categoriesPurchased = clientMapCategoryPurchased.get(client1.getEmail());

            assertTrue(productsPurchased.size() > 0);
            assertTrue(categoriesPurchased.size() > 0);

        });
    }

    @Test
    public void getClientNeighbors() {

        Client clientNeighbors = new Client(0, "email", "phone");

        clientNeighbors.setMajor(art_aut_bab);
        clients.add(clientNeighbors);

        List<Client> neighbors = service.getClientNeighbors(client1, clients);

        assertNotNull(neighbors);
        assertEquals(1 ,neighbors.size());
        assertEquals(clientNeighbors, neighbors.get(0));

    }

    @Test
    public void getClientNeighborsPurchases() {

        Client clientNeighbors = new Client(0, "email", "phone");
        clientNeighbors.setMajor(art_aut_bab);

        Order order = new Order();
        order.setOrderLines(new ArrayList<OrderLine>(){{
            add(new OrderLine(sp6, 3));
            add(new OrderLine(sp7, 2));
            add(new OrderLine(sp8, 2));
        }});

        clientNeighbors.setOrders(new HashSet<Order>(){{ add(order);}});

        clients.add(clientNeighbors);

        HashMap<String,HashSet<Product>> neighborsPurchases = new HashMap<>();

        neighborsPurchases.put(clientNeighbors.getEmail(), new HashSet<Product>(){{
            add(product6);
            add(product7);
            add(product8);
        }});

        HashSet<Product> clientNeighborsPurchases = service.getClientNeighborsPurchases(new ArrayList<Client>(){{add(clientNeighbors);}}, neighborsPurchases);

        assertNotNull(clientNeighborsPurchases);

        assertEquals(3, clientNeighborsPurchases.size());

        assertTrue(clientNeighborsPurchases.contains(product6));
        assertTrue(clientNeighborsPurchases.contains(product7));
        assertTrue(clientNeighborsPurchases.contains(product8));

    }

    @Test
    public void getNonPurchasedProduct() {

        HashSet<Product> purchased = new HashSet<Product>(){{
            add(product1);
            add(product2);
            add(product3);
        }};

        HashSet<Product> all = new HashSet<Product>(){{
            add(product1);
            add(product2);
            add(product3);
            add(product5);
            add(product6);
        }};

        HashSet<Product> nonPurchasedProduct = service.getNonPurchasedProduct(purchased, all);

        assertNotNull(nonPurchasedProduct);
        assertEquals(2, nonPurchasedProduct.size());
        assertTrue(nonPurchasedProduct.contains(product5));
        assertTrue(nonPurchasedProduct.contains(product6));
    }

    @Test
    public void mapClientWithNeighbors() {
        Client clientNeighbors = new Client(0, "email", "phone");

        clientNeighbors.setMajor(art_aut_bab);
        clients.add(clientNeighbors);

        HashMap<String, List<Client>> clientMapNeighbors = new HashMap<>();
        service.mapClientWithNeighbors(client1, new ArrayList<Client>(){{add(clientNeighbors);}}, clientMapNeighbors);

        assertEquals(1, clientMapNeighbors.size());
        assertEquals(1, clientMapNeighbors.get(client1.getEmail()).size());
        assertEquals(clientNeighbors, clientMapNeighbors.get(client1.getEmail()).get(0));
    }

    @Test
    public void getClientNeighborsMinorCategories() {

        Client clientNeighbors = new Client(0, "email", "phone");

        clientNeighbors.setMinor(art_aut_bab);

        clients.add(clientNeighbors);

        HashSet<ProductCategory> clientNeighborsMinorCategories = service.getClientNeighborsMinorCategories(new ArrayList<Client>() {{
            add(clientNeighbors);
        }});

        assertNotNull(clientNeighborsMinorCategories);
        assertEquals(3, clientNeighborsMinorCategories.size());
        assertTrue(clientNeighborsMinorCategories.contains(art));
        assertTrue(clientNeighborsMinorCategories.contains(aut));
        assertTrue(clientNeighborsMinorCategories.contains(bab));
    }

    @Test
    public void getNonPurchasedProductCategory() {

        HashSet<ProductCategory> purchased = new HashSet<ProductCategory>(){{
            add(art);
            add(aut);
            add(bab);
        }};

        HashSet<ProductCategory> all = new HashSet<ProductCategory>(){{
            add(art);
            add(aut);
            add(bab);
            add(spo);
            add(toy);
        }};

        HashSet<ProductCategory> nonPurchasedProductCategories = service.getNonPurchasedProductCategory(purchased, all);

        assertNotNull(nonPurchasedProductCategories);
        assertEquals(2, nonPurchasedProductCategories.size());
        assertTrue(nonPurchasedProductCategories.contains(spo));
        assertTrue(nonPurchasedProductCategories.contains(toy));

    }

    @Test
    public void getRecommendedProductFromCategory() {
        HashMap<String, LinkedHashSet<Product>> categoryMapProduct = new HashMap<>();

        categoryMapProduct.put(art.getName(), new LinkedHashSet<Product>(){{
            add(product1);
            add(product2);
            add(product3);
            add(product4);
            add(product5);
        }});

        LinkedHashSet<Product> recommendedProductFromCategory = service.getRecommendedProductFromCategory(2, art, categoryMapProduct);
        assertNotNull(recommendedProductFromCategory);
        assertEquals(2, recommendedProductFromCategory.size());
        assertTrue(recommendedProductFromCategory.contains(product1));
        assertTrue(recommendedProductFromCategory.contains(product2));
    }

    @Test
    public void getBestSellingProductCategories() {


        HashMap<String, LinkedHashSet<Product>> categoryMapProduct = productCategories.stream().collect(toMap(ProductCategory::getName, category -> new LinkedHashSet<>(), (a, b) -> b, HashMap::new));

        products.forEach(product -> {
            ProductCategory category = product.getProductCategory();
            LinkedHashSet<Product> products = categoryMapProduct.get(category.getName());
            products.add(product);
            categoryMapProduct.put(category.getName(), products);
        });


        HashMap<Product, Integer> productMapOrderCount = service.mapProductWithOrderCount(products, orderLines);

        HashMap<String, Integer> map = service.mapCategoryWithPopularity(categoryMapProduct, productMapOrderCount);

        HashSet<ProductCategory> nonPurchased = new HashSet<ProductCategory>(){{
            add(art);
            add(aut);
            add(bab);
        }};

        LinkedHashSet<ProductCategory> bestSellingProductCategories = service.getBestSellingProductCategories(nonPurchased, 1, map);

        assertNotNull(bestSellingProductCategories);
        assertEquals(1, bestSellingProductCategories.size());
        assertTrue(bestSellingProductCategories.contains(art));

    }

}