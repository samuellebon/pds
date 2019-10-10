package kan10.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import kan10.entities.QProduct;
import kan10.entities.QStoreProduct;

public class StoreProductPredicate {

    public static BooleanExpression hasName(String name) {
        if (name == null || name.isEmpty()) return QProduct.product.isNotNull();
        return QStoreProduct.storeProduct.product.name.containsIgnoreCase(name);
    }

    public static BooleanExpression hasCategory(String category) {
        if (category == null || category.isEmpty()) return QProduct.product.isNotNull();
        return QStoreProduct.storeProduct.product.productCategory.name.eq(category);
    }

    public static BooleanExpression hasStore(String store) {
        return QStoreProduct.storeProduct.store.label.eq(store);
    }

    public static BooleanExpression priceLesserOrEqual(double price) {
        return QStoreProduct.storeProduct.price.loe(price);
    }

    public static BooleanExpression priceGreaterOrEqual(double price) {
        return QStoreProduct.storeProduct.price.goe(price);
    }

}