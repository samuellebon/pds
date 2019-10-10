package kan10.api;

import com.querydsl.core.BooleanBuilder;

import kan10.entities.Store;
import kan10.service.StoreServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;

@RestController
public class StoreApi {

    @Autowired
    StoreServiceImpl storeService;

    @RequestMapping(value = "/store", method = RequestMethod.POST)
    public DataTablesOutput<Store> searchStore(@Valid @RequestBody DataTablesInput input, @RequestParam Map<String, String> requestParameters) {

        String category = requestParameters.get("category");
        String floor_level = requestParameters.get("floor_level");
        String aisle = requestParameters.get("aisle");
        String area_min = requestParameters.get("area_min");
        String area_max = requestParameters.get("area_max");
        String rent_min = requestParameters.get("rent_min");
        String rent_max = requestParameters.get("rent_max");

        BooleanBuilder boolB = new BooleanBuilder();

        if (category != null && !category.isEmpty())
            boolB.and(storeService.hasStoreCategory(category));

        if (floor_level != null && !floor_level.isEmpty())
            boolB.and(storeService.hasFloorLevelNumber(Integer.parseInt(floor_level)));

        if (aisle != null && !aisle.isEmpty())
            boolB.and(storeService.hasAisleLabel(aisle));

        if (area_min != null && !area_min.isEmpty()) {
            if (Integer.parseInt(area_min) != -1)  boolB.and(storeService.areaGreaterOrEqual(Integer.parseInt(area_min)));
        }


        if (area_max != null && !area_max.isEmpty()) {
            if (Integer.parseInt(area_max) != -1)  boolB.and(storeService.areaLesserOrEqual(Integer.parseInt(area_max)));
        }


        if (rent_min != null && !rent_min.isEmpty()) {
            if (Integer.parseInt(area_max) != -1)  boolB.and(storeService.rentGreaterOrEqual(Integer.parseInt(rent_min)));
        }

        if (rent_max != null && !rent_max.isEmpty()) {
            if (Integer.parseInt(rent_max) != -1)  boolB.and(storeService.rentLesserOrEqual(Integer.parseInt(rent_max)));
        }


        return storeService.findAll(input, boolB);
    }
}
