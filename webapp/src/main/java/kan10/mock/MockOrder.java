package kan10.mock;

import kan10.entities.*;
import kan10.interfaces.ProductService;
import kan10.interfaces.StoreService;
import kan10.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.time.Instant;
import java.time.temporal.TemporalAmount;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static java.lang.String.valueOf;
import static java.lang.System.nanoTime;
import static kan10.LogUtils.*;

class MockOrder {

    private static final int numberOfProduct = 10;

    private static final int numberOfStoreProduct = 15;

    private static final int numberOfavgOrderlinePerCLient = 5;

    private static final int minOrderPerCLient = 1;

    private static final int maxOrderPerCLient = 5;

    private static final Random r = new Random();

    private static Logger logger = LoggerFactory.getLogger(MockOrder.class);

    static void insertAll(ProductService productService, ProductCategoryServiceImpl productCategoryService,ClientService clientService, OrderService orderService, StoreProductServiceImpl storeProductService,StoreService storeService) {
        insertProduct(productService, productCategoryService);
        insertStoreProduct(storeProductService,storeService,productService);
        insertOrder(clientService,orderService,storeProductService);
    }

    /**
     *
     * This method create some products with a random category and a keyword (relative to the category attached).
     *
     * @param productService
     * @param productCategoryService
     */
    private static void insertProduct(ProductService productService, ProductCategoryServiceImpl productCategoryService) {
        ArrayList<ProductCategory> productCategories = (ArrayList<ProductCategory>) productCategoryService.getAllProductsCategories();

        for (int i = 1; i <= numberOfProduct; i++) {

            ProductCategory category = productCategories.get(r.nextInt(productCategories.size()));

            productService.createProduct(
                    new Product("name " + i,
                            "description_" + i,
                            1,
                            1,
                            1,
                            1,
                            category,
                            category.getKeywords().get(r.nextInt(category.getKeywords().size()))
                    )
            );
        }
    }

    /**
     *
     * This method create StoreProducts related to a store, with a random price and stock.
     *
     * Explanation or the method (to create one StoreProduct) :
     *
     *  - We get a random store and extract all product categories that this store should sell
     *  - We pick a random product and extract its category name
     *  - We try to see if the random product category match any categories of the store
     *      - If yes then can create a StoreProduct (Product and Store category are matching)
     *      - If no then we take another random product and repeat the process
     *
     *
     * @param storeProductService
     * @param storeService
     * @param productService
     */
    private static void insertStoreProduct(StoreProductServiceImpl storeProductService, StoreService storeService, ProductService productService) {
        ArrayList<Store> stores = (ArrayList<Store>) storeService.getAllStores();
        ArrayList<Product> products = (ArrayList<Product>) productService.getAllProducts();

        for (int i = 1; i <= numberOfStoreProduct; i++) {

            Store store = stores.get(r.nextInt(stores.size()));
            Set<ProductCategory> categories = store.getProductCategories();

            List<Product> similarProduct = products.stream().filter(
                    product -> categories.stream()
                            .anyMatch(category -> category.getName().equals(product.getProductCategory().getName()))
            ).collect(Collectors.toCollection(ArrayList::new));

            if (similarProduct.size() > 0) {
                Product product = similarProduct.get(r.nextInt(similarProduct.size()));

                storeProductService.createStoreProduct(
                        new StoreProduct(product,
                                store,
                                new Random().nextInt(1000),
                                new Random().nextInt(1000)
                        )
                );
            }

        }

    }

    /**
     * This method generate orders according to each client profile.
     *
     * Explanation of the method (for one client) :
     *
     *  - We get all product categories according to its major profile
     *  - We get all StoreProduct from the database
     *  - We filter all StoreProduct that has a product category matching one of the client major profile
     *      - If the filtered StoreProduct list  is empty, the client could not buy any StoreProduct due to its major profile
     *      - Else, we can generate random order
     *
     * @param clientService
     * @param orderService
     * @param storeProductService
     */
    private static void insertOrder(ClientService clientService, OrderService orderService, StoreProductServiceImpl storeProductService) {

        List<Client> clients = clientService.getAllClients();

        long startTime = nanoTime();

        int count = 1;
        for(Client client : clients) {

            logger.info("Client processed -> " + yellow(valueOf(count)) + " Time elapsed >>> " + cyan(valueOf(TimeUnit.SECONDS.convert((nanoTime() - startTime), TimeUnit.NANOSECONDS))) + " seconds");

            List<ProductCategory> productCategories = client.getMajor().getProductCategories();

            List<StoreProduct> storeProducts = storeProductService.getAllStoreProducts();

            List<StoreProduct> filteredArticles;

            filteredArticles = storeProducts.parallelStream()
                    .filter(
                            art -> productCategories.stream()
                                    .anyMatch(cat -> cat.getName().equals(art.getProduct().getProductCategory().getName()))
                    ).collect(Collectors.toList());

            int availableToPurchase = filteredArticles.size();

            if (!filteredArticles.isEmpty()) {

                for (int i = 1; i < r.nextInt(maxOrderPerCLient + 1 - minOrderPerCLient) + minOrderPerCLient; i++) {

                    Order order = new Order();

                    for (int j = 1; j <= r.nextInt(numberOfavgOrderlinePerCLient)+1; j++) {
                        order.addOrderline(
                                new OrderLine(
                                        filteredArticles.get(r.nextInt(availableToPurchase)),
                                        10
                                )
                        );
                    } // end [ OrderLines generation ]

                    orderService.createOrder(order);
                    client.addOrder(order);

                } // end [ Orders generation]

                clientService.updateClient(client);

            } // end [ if (!filteredArticles.isEmpty()) ]
            count++;
        } // end [ for(Client client : clients) ]

        logger.info("");
        logger.info("Processed " + yellow(valueOf(clients.size())) + " clients in " + cyan(TimeUnit.SECONDS.convert((nanoTime() - startTime), TimeUnit.NANOSECONDS) + " minutes"));
    }
}