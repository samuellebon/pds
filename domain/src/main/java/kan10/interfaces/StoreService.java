package kan10.interfaces;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.Predicate;

import kan10.entities.Store;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;

import java.util.List;

public interface StoreService {


    List <Store>  getAllStores();

    Store saveStoreToDb(Store store);

    BooleanExpression hasStoreCategory(String storeCat);

    BooleanExpression hasFloorLevelNumber(int floorLevel);

    BooleanExpression hasAisleLabel(String aisle);

    BooleanExpression areaGreaterOrEqual(int areaMin);

    BooleanExpression areaLesserOrEqual(int areaMax);

    BooleanExpression rentGreaterOrEqual(int rentMin);

    BooleanExpression rentLesserOrEqual(int rentMax);

    DataTablesOutput<Store> findAll(DataTablesInput input, Predicate predicate);

    DataTablesOutput<Store> findAll(DataTablesInput input);
}
