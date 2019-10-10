package kan10.service;

import kan10.entities.*;
import kan10.interfaces.IRecommenderSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;
import static java.lang.String.valueOf;
import static java.util.Collections.*;
import static java.util.Collections.reverseOrder;
import static java.util.Comparator.comparing;
import static java.util.Comparator.reverseOrder;
import static java.util.Map.*;
import static java.util.Map.Entry.*;
import static java.util.stream.Collectors.*;
import static java.util.stream.Collectors.toMap;
import static kan10.LogUtils.*;

@Service
public class RecommenderSystemService implements IRecommenderSystemService {

    private final ClientService clientService;

    private final ProductServiceImpl productService;

    private final ProductCategoryServiceImpl productCategoryService;

    private final OrderLineService orderLineService;

    private final Logger logger;

    private final StringBuilder builder;

    {
        this.logger = LoggerFactory.getLogger(RecommenderSystemService.class);
        this.builder = new StringBuilder();
    }


    public RecommenderSystemService(ClientService clientService, ProductServiceImpl productService, ProductCategoryServiceImpl productCategoryService, OrderLineService orderLineService) {
        this.clientService = clientService;
        this.productService = productService;
        this.productCategoryService = productCategoryService;
        this.orderLineService = orderLineService;
    }

    void startRecommendationsEngine(List<Client> targetedClients) {

        List<Client> allClients = clientService.getAllClients();
        List<Product> allProducts = productService.getAllProducts();
        List<ProductCategory> allProductCategories = productCategoryService.getAllProductsCategories();
        List<OrderLine> allOrderLines = orderLineService.getAllOrderLines();

        info("---------------------------< " + cyan("RECOMMENDER SYSTEM STARTED") + " >----------------------------");

        newLine();
        debug(">>> " + green("Data repartition") + " >>> @");
        newLine();
        debug("Total of client : " + targetedClients.size());
        debug("Total of product categories : " + allProductCategories.size());
        debug("Total of products : " + allProducts.size());
        debug("Total of purchases : " + allOrderLines.stream().mapToInt(OrderLine::getQuantity).sum());
        newLine();

        // Map each product to its purchase count
        HashMap<Product, Integer> productMapOrderCount = mapProductWithOrderCount(allProducts, allOrderLines);

        // We sort the map based on on the purchase count of each product
        HashMap<Product, Integer> sortByBuyCount = sortProductsByPopularity(productMapOrderCount);

        debug(">>> " + green("Purchase count per product (Sorted by best-sellers)" + " >>> @ " + cyan("[Keyword] [Name] -> {count} purchase(s)")));
        newLine();

        logProductWithOrderCount(sortByBuyCount);

        // We fill the [productCategory -> products] map sorted by each product purchase
        HashMap<String, LinkedHashSet<Product>> categoryMapProduct = mapCategoryWithProduct(allProductCategories, sortByBuyCount);

        newLine();
        debug(">>> " + green("Product count per category") + " >>> @ " + cyan("{Category} -> {count} product(s)"));
        newLine();

        HashMap<String, Integer> categoryMapPopularity = mapCategoryWithPopularity(categoryMapProduct, productMapOrderCount);
        logCategoryWithProductCount(categoryMapProduct);

        newLine();
        debug(">>> " + green("Popularity per category") + " >>> @ " + cyan("[category] >>> {count} order line(s)"));
        newLine();

        HashMap<String, Integer> categoryMapPopularitySorted = sortCategoryByPopularity(categoryMapPopularity);

        logCategoryWithPopularity(categoryMapPopularitySorted);

        newLine();
        debug(">>> " + green("Purchase count per category") + " >>> @ " + cyan("[Keyword] [Name] -> {count} purchase(s)"));
        newLine();

        logProductWithPopularityGroupedByCategory(categoryMapProduct, productMapOrderCount);

        debug(">>> " + green("Product brought per client") + " >>> @ " + cyan("name -> (count) : {list}"));
        newLine();

        HashMap<String, HashSet<Product>> clientMapProductPurchased = new HashMap<>();
        HashMap<String, HashSet<ProductCategory>> clientMapCategoryPurchased = new HashMap<>();

        mapClientsProductsAndCategoriesPurchased(allClients, clientMapProductPurchased, clientMapCategoryPurchased);

        clientMapProductPurchased.forEach((email, products) -> {
            int purchasesCount = products.size();

            products.forEach(product -> builder.append(product.getName()).append(" - "));
            if (purchasesCount <= 0) debug(yellow(email) + " -> " + red(" <!> No purchases <!>"));
            else debug(yellow(email) + " -> (" + purchasesCount + ") : " + "{" + builder + "}");

            resetBuilder();
        });

        HashMap<String, HashSet<Product>> clientMapRecommendationsUBCF = new HashMap<>();

        newLine();
        info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        info("---------------------------< " + cyan("Application of User Based Collaborative Filtering") + " >----------------------------");
        info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        newLine();

        HashMap<String, List<Client>> clientMapNeighbors = new HashMap<>();

        allClients.forEach(client -> mapClientWithNeighbors(client, getClientNeighbors(client, allClients), clientMapNeighbors));

        targetedClients.forEach(
                client -> {
                    applyUserBasedCollaborativeFiltering(client, clientMapProductPurchased, clientMapNeighbors.get(client.getEmail()), clientMapRecommendationsUBCF);
                }
        );

        HashMap<String, List<Product>> clientMapRecommendationsCBF = targetedClients.stream().collect(toMap(Client::getEmail, client -> new ArrayList<>(), (a, b) -> b, HashMap::new));

        TreeMap<String, List<Product>> clientMapFinalRecommendations = new TreeMap<>(comparing(s -> parseInt(s.substring(s.indexOf("_") + 1, s.indexOf("@")))));

        clientMapRecommendationsUBCF.forEach((email, products) -> clientMapFinalRecommendations.put(email, new ArrayList<>(products)));

        newLine();
        info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        info("---------------------------< " + cyan("Application of Content Based Filtering") + " >----------------------------");
        info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        newLine();

        targetedClients.forEach(
                client -> applyContentBasedFiltering(client,
                        clientMapNeighbors.get(client.getEmail()),
                        new HashSet<>(allProductCategories),
                        categoryMapProduct, clientMapCategoryPurchased,
                        categoryMapPopularity, clientMapRecommendationsCBF,
                        clientMapFinalRecommendations)
        );

        newLine();
        info(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        info("---------------------------< " + cyan("EMITTED RECOMMENDATIONS") + " >----------------------------");
        info("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
        newLine();

        clientMapFinalRecommendations.forEach((email, products) -> {

            info(">>> " + yellow(email) + " >>> @ " + magenta("UBCF") + " | " + green("CBF"));
            newLine();

            List<Product> sortedProductRecommendation = products.stream().sorted(reverseOrder(comparing(productMapOrderCount::get))).collect(Collectors.toList());

            HashMap<String, LinkedHashSet<Product>> categoryMapRecommendations = new HashMap<>();
            allProductCategories.forEach(productCategory -> categoryMapRecommendations.put(productCategory.getName(), new LinkedHashSet<>()));

            sortedProductRecommendation.forEach(product -> {
                String category = product.getProductCategory().getName();
                LinkedHashSet<Product> productFromCategory = categoryMapRecommendations.get(category);
                productFromCategory.add(product);
                categoryMapRecommendations.put(category, productFromCategory);
            });

            categoryMapRecommendations.forEach((category, suggestedProducts) -> {

                int suggestedProductCount = suggestedProducts.size();

                if (suggestedProductCount > 0) {

                    suggestedProducts.forEach(product -> {
                        if (clientMapRecommendationsCBF.get(email).contains(product)) builder.append(green(product.getName()));
                        else if (clientMapRecommendationsUBCF.get(email).contains(product)) builder.append(magenta(product.getName()));
                        else builder.append(red("<!> <!> <!>"));
                        builder.append(" - ");
                    });

                    info(category + " (" + suggestedProductCount + ") -> {" + builder + "}");
                    resetBuilder();
                }
            });
            newLine();
        });

        info("---------------------------< " + red("END") + " >----------------------------");

    }

    @Override
    public HashMap<Product, Integer> mapProductWithOrderCount(List<Product> products, List<OrderLine> orderLines) {
        HashMap<Product, Integer> productMapOrderCount = products.stream().collect(toMap(product -> product, product -> 0, (a, b) -> b, HashMap::new));

        orderLines.forEach(
                orderLine -> {
                    Product product = orderLine.getStoreProduct().getProduct();
                    Integer purchaseCount = productMapOrderCount.get(product);
                    purchaseCount += orderLine.getQuantity();
                    productMapOrderCount.put(product, purchaseCount);
                }
        );
        return productMapOrderCount;
    }

    @Override
    public HashMap<String, LinkedHashSet<Product>> mapCategoryWithProduct(List<ProductCategory> productCategories, HashMap<Product, Integer> productMapPurchasesCount) {
        HashMap<String, LinkedHashSet<Product>> categoryMapProduct = productCategories.stream().collect(toMap(ProductCategory::getName, category -> new LinkedHashSet<>(), (a, b) -> b, HashMap::new));

        productMapPurchasesCount.forEach((product, count) -> {
            ProductCategory category = product.getProductCategory();
            LinkedHashSet<Product> products = categoryMapProduct.get(category.getName());
            products.add(product);
            categoryMapProduct.put(category.getName(), products);
        });

        return categoryMapProduct;
    }

    @Override
    public HashMap<String, Integer> mapCategoryWithPopularity(HashMap<String, LinkedHashSet<Product>> categoryMapProduct, HashMap<Product, Integer> productMapOrderCount) {
        HashMap<String, Integer> categoryMapPopularity = new HashMap<>();

        categoryMapProduct.forEach((category, products) -> categoryMapPopularity.put(category, products.stream().mapToInt(productMapOrderCount::get).sum()));
        return categoryMapPopularity;
    }

    @Override
    public HashMap<Product, Integer> sortProductsByPopularity(HashMap<Product, Integer> map) {
        return map.entrySet().stream()
                .sorted(reverseOrder(comparingByValue()))
                .collect(toMap(Entry::getKey, Entry::getValue, (o, n) -> o, LinkedHashMap::new));
    }

    @Override
    public HashMap<String, Integer> sortCategoryByPopularity(HashMap<String, Integer> categoryMapPopularity) {
        return categoryMapPopularity.entrySet().stream()
                .sorted(Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(toMap(Entry::getKey, Entry::getValue, (o, n) -> o, LinkedHashMap::new));
    }

    @Override
    public void mapProductAndCategoryPurchased(Client client, HashMap<String, HashSet<Product>> clientMapProductPurchased, HashMap<String, HashSet<ProductCategory>> clientMapCategoryPurchased) {
        Set<Order> orders = client.getOrders().stream()
                .sorted(reverseOrder(comparing(Order::getDate)))
                .collect(toCollection(LinkedHashSet::new));

        if (orders.size() <= 0) {
            clientMapProductPurchased.put(client.getEmail(), new HashSet<>());
            clientMapCategoryPurchased.put(client.getEmail(), new HashSet<>());
        } else {
            orders.forEach(order -> order.getOrderLines().forEach(orderLine -> {

                Product product = orderLine.getStoreProduct().getProduct();
                ProductCategory category = product.getProductCategory();

                HashSet<Product> productHashSet = clientMapProductPurchased.get(client.getEmail());
                HashSet<ProductCategory> productCategoryHashSet = clientMapCategoryPurchased.get(client.getEmail());

                if (productHashSet == null) productHashSet = new HashSet<>();
                if (productCategoryHashSet == null) productCategoryHashSet = new HashSet<>();

                productHashSet.add(product);
                productCategoryHashSet.add(category);

                clientMapProductPurchased.put(client.getEmail(), productHashSet);
                clientMapCategoryPurchased.put(client.getEmail(), productCategoryHashSet);

            }));
        }
    }

    @Override
    public void mapClientsProductsAndCategoriesPurchased(List<Client> clients, HashMap<String, HashSet<Product>> clientMapProductPurchased, HashMap<String, HashSet<ProductCategory>> clientMapCategoryPurchased) {
        clients.forEach(client -> mapProductAndCategoryPurchased(client, clientMapProductPurchased, clientMapCategoryPurchased));
    }

    @Override
    public List<Client> getClientNeighbors(Client client, List<Client> clients) {
        return clients.stream().filter(c -> !c.equals(client) && c.getMajor().equals(client.getMajor())).collect(toList());
    }

    @Override
    public HashSet<Product> getClientNeighborsPurchases(List<Client> clientNeighbors, HashMap<String, HashSet<Product>> clientMapProductPurchased) {
        HashSet<Product> clientNeighborsPurchases = new HashSet<>();
        clientNeighbors.forEach(c -> clientNeighborsPurchases.addAll(clientMapProductPurchased.get(c.getEmail())));
        return clientNeighborsPurchases;
    }

    @Override
    public HashSet<Product> getNonPurchasedProduct(HashSet<Product> productsAlreadyPurchased, HashSet<Product> clientNeighborsPurchases) {
        clientNeighborsPurchases.removeIf(productsAlreadyPurchased::contains);
        return clientNeighborsPurchases;
    }

    @Override
    public void mapClientWithNeighbors(Client client, List<Client> neighbors, HashMap<String, List<Client>> clientMapNeighbors) {
        clientMapNeighbors.put(client.getEmail(), neighbors);
    }

    @Override
    public void applyUserBasedCollaborativeFiltering(Client client, HashMap<String, HashSet<Product>> clientMapProductPurchased, List<Client> neighbors, HashMap<String, HashSet<Product>> clientMapRecommendationsUBCF) {
        info("---------------------------< " + magenta(client.getEmail()) + " >----------------------------");
        newLine();

        HashSet<Product> productsAlreadyPurchased = clientMapProductPurchased.get(client.getEmail());
        HashSet<Product> clientNeighborsPurchases = getClientNeighborsPurchases(neighbors, clientMapProductPurchased);

        productsAlreadyPurchased.forEach(p -> builder.append(p.getName()).append(" - "));
        debug("Product purchased (" + productsAlreadyPurchased.size() + ") : " + builder);
        resetBuilder();
        newLine();

        logDifferenceBetweenClientAndNeighborsPurchases(neighbors, productsAlreadyPurchased, clientMapProductPurchased);
        newLine();

        HashSet<Product> recommendedProducts = getNonPurchasedProduct(productsAlreadyPurchased, clientNeighborsPurchases);
        int recommendationCount = recommendedProducts.size();

        if (recommendationCount <= 0) info(red("No recommendation could be made by UBCF..."));
        else {
            recommendedProducts.forEach(product -> builder.append(green(product.getName())).append(" - "));
            info("Recommendations from neighbors (" + green(valueOf(recommendationCount)) + ") : { " + builder.toString() + " }");
            resetBuilder();
        }
        newLine();
        clientMapRecommendationsUBCF.put(client.getEmail(), new HashSet<>(recommendedProducts));
    }

    @Override
    public HashSet<ProductCategory> getClientNeighborsMinorCategories(List<Client> clientNeighbors) {
        HashSet<ProductCategory> minorCategories = new HashSet<>();
        clientNeighbors.forEach(c -> minorCategories.addAll(c.getMinor().getProductCategories()));
        return minorCategories;
    }

    @Override
    public HashSet<ProductCategory> getNonPurchasedProductCategory(HashSet<ProductCategory> productCategoriesAlreadyPurchased, HashSet<ProductCategory> clientNeighborsPurchases) {
        HashSet<ProductCategory> clientNeighborsPurchasesCopy = new HashSet<>(clientNeighborsPurchases);
        clientNeighborsPurchasesCopy.removeIf(productCategoriesAlreadyPurchased::contains);
        return clientNeighborsPurchasesCopy;
    }

    @Override
    public LinkedHashSet<Product> getRecommendedProductFromCategory(int numberOfProductToBeRecommended, ProductCategory recommendedCategory, HashMap<String, LinkedHashSet<Product>> categoryMapProduct) {
        return categoryMapProduct.get(recommendedCategory.getName()).stream().limit(numberOfProductToBeRecommended).collect(toCollection(LinkedHashSet::new));
    }

    @Override
    public void recommendBestSellersFromProductCategories(Client client, int numberOfProductToBeRecommended, HashSet<ProductCategory> productCategories, HashSet<Product> productsToBeRecommended, HashMap<String, LinkedHashSet<Product>> categoryMapProduct, HashMap<String, List<Product>> clientMapRecommendationsCBRF, TreeMap<String, List<Product>> clientMapFinalRecommendations) {
        productCategories.forEach(
                productCategory -> {
                    List<Product> recommendedProducts = clientMapRecommendationsCBRF.get(client.getEmail());

                    LinkedHashSet<Product> recommendedProductFromCategory = getRecommendedProductFromCategory(numberOfProductToBeRecommended, productCategory, categoryMapProduct);

                    recommendedProductFromCategory.forEach(p -> builder.append(green(p.getName())).append(" - "));

                    recommendedProducts.addAll(recommendedProductFromCategory);

                    clientMapRecommendationsCBRF.put(client.getEmail(), new ArrayList<>(recommendedProducts));
                    productsToBeRecommended.addAll(recommendedProductFromCategory);
                    clientMapFinalRecommendations.put(client.getEmail(), new ArrayList<>(productsToBeRecommended));

                    info("Recommendation from category (" + productCategory.getName() + ") : {" + builder + "}");
                    resetBuilder();
                }
        );
    }

    @Override
    public LinkedHashSet<ProductCategory> getBestSellingProductCategories(HashSet<ProductCategory> productCategories, int numberOfProductCategoryToBeRetained, HashMap<String, Integer> categoryMapPopularity) {
        return productCategories.stream()
                .sorted(reverseOrder(comparing(productCategory -> categoryMapPopularity.get(productCategory.getName()))))
                .limit(numberOfProductCategoryToBeRetained)
                .collect(toCollection(LinkedHashSet::new));
    }

    @Override
    public void applyContentBasedFiltering(Client client, List<Client> neighbors, HashSet<ProductCategory> allProductCategories, HashMap<String, LinkedHashSet<Product>> categoryMapProduct, HashMap<String, HashSet<ProductCategory>> clientMapCategoryPurchased, HashMap<String, Integer> categoryMapPopularity, HashMap<String, List<Product>> clientMapRecommendationsCBF, TreeMap<String, List<Product>> clientMapFinalRecommendations) {
        info("---------------------------< " + magenta(client.getEmail()) + " >----------------------------");
        newLine();

        logClientInterests(client);

        HashSet<ProductCategory> minorCategories = new HashSet<>(client.getMinor().getProductCategories());

        int numberOfNeighbors = neighbors.size();
        HashSet<Product> finalRecommended = new HashSet<>(clientMapFinalRecommendations.get(client.getEmail()));
        resetBuilder();

        if (numberOfNeighbors > 0) {

            info(red("<!> Got neighbors >>> ") + yellow("Trying to recommend something new <!>"));
            newLine();

            HashSet<ProductCategory> clientNeighborsMinorCategories = getClientNeighborsMinorCategories(neighbors);
            HashSet<ProductCategory> recommendedCategories = getNonPurchasedProductCategory(minorCategories, clientNeighborsMinorCategories);

            logProductCategoriesRecommendations(minorCategories, clientNeighborsMinorCategories, recommendedCategories);

            if (recommendedCategories.size() > 0) {
                info(yellow(">>>"));
                newLine();

                recommendBestSellersFromProductCategories(client, 3, recommendedCategories, finalRecommended, categoryMapProduct, clientMapRecommendationsCBF, clientMapFinalRecommendations);

            } else {

                info(red("<!> Brought the same categories than neighbors.. >>> ") + yellow("Applying uncorrelated content based recommendation <!>"));
                newLine();

                HashSet<ProductCategory> productCategoriesInterestedIn = clientMapCategoryPurchased.get(client.getEmail());
                productCategoriesInterestedIn.addAll(minorCategories);

                HashSet<ProductCategory> foreignProductCategoriesToDiscover = getNonPurchasedProductCategory(productCategoriesInterestedIn, allProductCategories);
                logProductCategoriesRecommendations(productCategoriesInterestedIn, allProductCategories, foreignProductCategoriesToDiscover);

                foreignProductCategoriesToDiscover = getBestSellingProductCategories(foreignProductCategoriesToDiscover, 2, categoryMapPopularity);

                recommendBestSellersFromProductCategories(client, 3, foreignProductCategoriesToDiscover, finalRecommended, categoryMapProduct, clientMapRecommendationsCBF, clientMapFinalRecommendations);

            }

        } else {

            info(red("<!> No neighbors.. >>> ") + yellow("Recommendation from minor categories <!>"));
            newLine();

            recommendBestSellersFromProductCategories(client, 3, minorCategories, finalRecommended, categoryMapProduct, clientMapRecommendationsCBF, clientMapFinalRecommendations);
        }
        newLine();
    }

    private void logDifferenceBetweenClientAndNeighborsPurchases(List<Client> clientNeighbors, HashSet<Product> clientPurchases, HashMap<String, HashSet<Product>> clientMapProductPurchased) {

        int clientNeighborsCount = clientNeighbors.size();
        info("Number of neighbors found : " + yellow(valueOf(clientNeighborsCount)));

        if (clientNeighborsCount > 0) newLine();
        clientNeighbors.forEach(c -> {
            HashSet<Product> neighborsPurchases = clientMapProductPurchased.get(c.getEmail());

            neighborsPurchases.forEach(p -> {
                if (clientPurchases.contains(p)) builder.append(red(p.getName()));
                else builder.append(green(p.getName()));
                builder.append(" - ");
            });

            debug("Brought by " + c.getEmail() + " (" + neighborsPurchases.size() + ") : {" + builder + "}");
            resetBuilder();
        });

    }

    private void logProductCategoriesRecommendations(HashSet<ProductCategory> productCategoriesPurchased, HashSet<ProductCategory> productCategoriesToFilter, HashSet<ProductCategory> productCategoriesFiltered) {

        productCategoriesPurchased.forEach(c -> builder.append(c.getName()).append(" - "));
        info("P.categories purchased and/or interested in : " + builder);
        resetBuilder();

        if (productCategoriesToFilter.size() >= 11) info(">>> Filtering from all product categories <<<");
        else {
            productCategoriesToFilter.forEach(c -> {
                if (productCategoriesPurchased.contains(c)) builder.append(red(c.getName()));
                else builder.append(green(c.getName()));
                builder.append(" - ");
            });
            info("P.categories purchased from neighbors : " + builder);
            resetBuilder();
        }

        if (productCategoriesFiltered.size() <= 0) info(red("<!> <!> <!>"));
        else {
            productCategoriesFiltered.forEach(c -> builder.append(c.getName()).append(" - "));
            info("Product category to discover : " + builder);
            resetBuilder();
            newLine();
        }

    }

    private void logClientInterests(Client client) {

        new HashSet<>(client.getMajor().getProductCategories()).forEach(productCategory -> builder.append(productCategory.getName()).append(" - "));
        info("Major profile categories : " + builder);
        resetBuilder();

        new HashSet<>(client.getMinor().getProductCategories()).forEach(productCategory -> builder.append(productCategory.getName()).append(" - "));
        info("Minor profile categories : " + builder);
        newLine();

    }

    private void logProductWithPopularityGroupedByCategory(HashMap<String, LinkedHashSet<Product>> categoryMapProduct, HashMap<Product, Integer> productMapOrderCount) {
        categoryMapProduct.forEach((category, products) -> {
            debug("--- " + cyan(category) + " ---");
            newLine();

            products.forEach(product -> {

                int count = productMapOrderCount.get(product);

                builder.append("[").append(product.getKeywords()).append("] [").append(product.getName()).append("] -> ");
                if (count == 0) builder.append(red(valueOf(count)));
                else builder.append(green(valueOf(count)));

                builder.append(" purchase(s)");
                debug(builder.toString());
                resetBuilder();
            });
            newLine();
        });
    }

    private void logCategoryWithPopularity(HashMap<String, Integer> categoryMapPopularitySorted) {

        ArrayList<Integer> popularityCount = new ArrayList<>(categoryMapPopularitySorted.values());
        Integer minCategoryPopularity = popularityCount.stream().min(comparing(Integer::valueOf)).orElse(-1);
        Integer maxCategoryPopularity = popularityCount.stream().max(comparing(Integer::valueOf)).orElse(-1);

        categoryMapPopularitySorted.forEach((category, count) -> {
            builder.append(category).append(" >>> ");
            if (count.equals(maxCategoryPopularity)) builder.append(green(valueOf(count)));
            else if (count.equals(minCategoryPopularity)) builder.append(red(valueOf(count)));
            else builder.append(yellow(valueOf(count)));
            builder.append(" purchase(s)");
            debug(builder.toString());
            resetBuilder();
        });

    }

    private void logProductWithOrderCount(HashMap<Product, Integer> sortByBuyCount) {

        sortByBuyCount.forEach((product, count) -> {
            String keyword = product.getKeywords();
            String name = product.getName();

            builder.append("[").append(keyword).append("] [").append(name).append("] -> ");
            if (count == 0) builder.append(red(valueOf(count)));
            else builder.append(green(valueOf(count)));
            builder.append(" purchase(s)");

            debug(builder.toString());
            resetBuilder();
        });

    }

    private void logCategoryWithProductCount(HashMap<String, LinkedHashSet<Product>> categoryMapProducts) {
        categoryMapProducts.forEach((category, products) -> debug(category + " -> " + cyan(valueOf(products.size())) + " product(s)"));
    }

    private void newLine() {
        info("");
    }

    private void info(String s) {
        logger.info(s);
    }

    private void debug(String s) {
        logger.debug(s);
    }

    private void resetBuilder() {
        this.builder.replace(0, this.builder.length(), "");
    }
}