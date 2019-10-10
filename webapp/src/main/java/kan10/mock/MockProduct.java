package kan10.mock;

import kan10.entities.*;
import kan10.interfaces.ProductService;
import kan10.service.ProductCategoryServiceImpl;
import kan10.interfaces.StoreService;
import kan10.service.StoreProductServiceImpl;


import java.util.*;


class MockProduct {

    private static final Random r = new Random();

    /**
     * create Product
     */

    private static void insertProduct(ProductService productService, ProductCategoryServiceImpl productCategoryService) {
        ArrayList<ProductCategory> productCategories = (ArrayList<ProductCategory>) productCategoryService.getAllProductsCategories();

        for (int i = 1; i <= 100; i++) {

            ProductCategory category = productCategories.get(r.nextInt(productCategories.size()));

            productService.createProduct(
                    new Product("name " + i, "description_" + i, 1, 1, 1, 1, category, category.getKeywords().get(r.nextInt(category.getKeywords().size()))
                    )
            );
        }
    }


    /**
     * create storeProduct
     */

    private static void insertStoreProduct(StoreProductServiceImpl storeProductService, StoreService storeService, ProductService productService) {

        ArrayList<Store> stores = (ArrayList<Store>) storeService.getAllStores();
        ArrayList<Product> products = (ArrayList<Product>) productService.getAllProducts();

        for (int i = 1; i <= 100; i++) {

            Store store = stores.get(r.nextInt(stores.size()));


            Product product = products.get(r.nextInt(products.size()));

            Product finalProduct = product;

            storeProductService.createStoreProduct(
                    new StoreProduct(finalProduct,
                            store,
                            new Random().nextInt(100),
                            new Random().nextInt(100)
                    )
            );

        }

    }
}
