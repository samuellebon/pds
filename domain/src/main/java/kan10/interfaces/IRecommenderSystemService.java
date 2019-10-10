package kan10.interfaces;

import kan10.entities.Client;
import kan10.entities.OrderLine;
import kan10.entities.Product;
import kan10.entities.ProductCategory;

import java.util.*;

public interface IRecommenderSystemService {

    HashMap<Product, Integer> mapProductWithOrderCount(
            List<Product> products,
            List<OrderLine> orderLines
    );

    HashMap<String, LinkedHashSet<Product>> mapCategoryWithProduct(
            List<ProductCategory> productCategories,
            HashMap<Product, Integer> productMapPurchasesCount
    );

    HashMap<String, Integer> mapCategoryWithPopularity(
            HashMap<String, LinkedHashSet<Product>> categoryMapProduct,
            HashMap<Product, Integer> productMapOrderCount
    );

    HashMap<Product, Integer> sortProductsByPopularity(
            HashMap<Product, Integer> map
    );

    HashMap<String, Integer> sortCategoryByPopularity(
            HashMap<String, Integer> categoryMapPopularity
    );

    void mapProductAndCategoryPurchased(
            Client client,
            HashMap<String, HashSet<Product>> clientMapProductPurchased,
            HashMap<String, HashSet<ProductCategory>> clientMapCategoryPurchased
    );

    void mapClientsProductsAndCategoriesPurchased(
            List<Client> clients,
            HashMap<String, HashSet<Product>> clientMapProductPurchased,
            HashMap<String, HashSet<ProductCategory>> clientMapCategoryPurchased
    );

    List<Client> getClientNeighbors(
            Client client,
            List<Client> clients
    );

    HashSet<Product> getClientNeighborsPurchases(
            List<Client> clientNeighbors,
            HashMap<String,HashSet<Product>> clientMapProductPurchased
    );

    HashSet<Product> getNonPurchasedProduct(
            HashSet<Product> productsAlreadyPurchased,
            HashSet<Product> clientNeighborsPurchases
    );

    void mapClientWithNeighbors(
            Client client, List<Client> neighbors,
            HashMap<String, List<Client>> clientMapNeighbors
    );

    void applyUserBasedCollaborativeFiltering(
            Client client,
            HashMap<String, HashSet<Product>> clientMapProductPurchased,
            List<Client> neighbors,
            HashMap<String, HashSet<Product>> clientMapRecommendationsUBCF
    );

    HashSet<ProductCategory> getClientNeighborsMinorCategories(
            List<Client> clientNeighbors
    );

    HashSet<ProductCategory> getNonPurchasedProductCategory(
            HashSet<ProductCategory> productCategoriesAlreadyPurchased,
            HashSet<ProductCategory> clientNeighborsPurchases
    );

    LinkedHashSet<Product> getRecommendedProductFromCategory(
            int numberOfProductToBeRecommended,
            ProductCategory recommendedCategory,
            HashMap<String, LinkedHashSet<Product>> categoryMapProduct
    );

    void recommendBestSellersFromProductCategories(
            Client client,
            int numberOfProductToBeRecommended,
            HashSet<ProductCategory> productCategories,
            HashSet<Product> productsToBeRecommended,
            HashMap<String, LinkedHashSet<Product>> categoryMapProduct,
            HashMap<String, List<Product>> clientMapRecommendationsCBRF,
            TreeMap<String, List<Product>> clientMapFinalRecommendations
    );

    LinkedHashSet<ProductCategory> getBestSellingProductCategories(
            HashSet<ProductCategory> productCategories,
            int numberOfProductCategoryToBeRetained,
            HashMap<String, Integer> categoryMapPopularity
    );

    void applyContentBasedFiltering(
            Client client,
            List<Client> neighbors,
            HashSet<ProductCategory> allProductCategories,
            HashMap<String, LinkedHashSet<Product>> categoryMapProduct,
            HashMap<String, HashSet<ProductCategory>> clientMapCategoryPurchased,
            HashMap<String, Integer> categoryMapPopularity,
            HashMap<String, List<Product>> clientMapRecommendationsCBF,
            TreeMap<String, List<Product>> clientMapFinalRecommendations
    );

}
