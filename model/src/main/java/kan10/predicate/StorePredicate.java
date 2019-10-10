package kan10.predicate;

import com.querydsl.core.types.dsl.BooleanExpression;
import kan10.entities.QStore;

public class StorePredicate {
    private StorePredicate() {
    }

    public static BooleanExpression hasStoreCategory(String labelCat) {
        if (labelCat == null || labelCat.isEmpty()) return QStore.store.isNotNull();
        return QStore.store.storeCategory.name.eq(labelCat);
    }

    public static BooleanExpression hasAisleLabel(String labelAisle) {
        if (labelAisle == null || labelAisle.isEmpty()) return QStore.store.isNotNull();
        return QStore.store.location.aisle.eq(labelAisle);
    }

    public static BooleanExpression hasFloorLevelNumber(int floorLevelNumber) {
        return QStore.store.location.floorLevel.eq(floorLevelNumber);
    }

    public static BooleanExpression areaGreaterOrEqual(int areaNumber) {
        return QStore.store.location.area.goe(areaNumber);
    }

    public static BooleanExpression areaLesserOrEqual(int areaNumber) {
        return QStore.store.location.area.loe(areaNumber);
    }

    public static BooleanExpression rentGreaterOrEqual(int rentNumber) {
        return QStore.store.location.rent.goe(rentNumber);
    }

    public static BooleanExpression rentLesserOrEqual(int rentNumber) {
        return QStore.store.location.rent.loe(rentNumber);
    }
}