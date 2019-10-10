package kan10.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import kan10.entities.QProduct;

public class ProductPredicate {
    private ProductPredicate() {
    }

    public static BooleanExpression hasName(String name) {
        if (name == null || name.isEmpty()) return QProduct.product.isNotNull();
        return QProduct.product.name.containsIgnoreCase(name);
    }

    public static BooleanExpression hasCategory(String categoryName) {
        if (categoryName == null || categoryName.isEmpty()) return QProduct.product.isNotNull();
        return QProduct.product.productCategory.name.eq(categoryName);
    }
}