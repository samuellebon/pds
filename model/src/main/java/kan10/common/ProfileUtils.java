package kan10.common;

import kan10.entities.ProductCategory;
import kan10.entities.Profile;

import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class ProfileUtils {

    private static boolean isValid(Profile major, Profile minor) {
        return major.getProductCategories().stream().noneMatch(p -> minor.getProductCategories().stream().anyMatch(p::equals));
    }

    public static Profile generateMinorProfile(Profile major, List<Profile> profiles) {
        Profile minor = profiles.get(new Random().nextInt(profiles.size()));
        while (!ProfileUtils.isValid(major, minor)) {
            Collections.shuffle(profiles);
            minor = profiles.get(new Random().nextInt(profiles.size()));
        }
        return minor;
    }

    public static Profile generateProfile(int distinctCategories, List<ProductCategory> productCategories) {
        Collections.shuffle(productCategories);
        return new Profile( getDistinct(distinctCategories, productCategories));
    }

    private static List<ProductCategory> getDistinct(int n, List<ProductCategory> productCategories ) {
     return productCategories.stream().limit(n).distinct().collect(Collectors.toList());
    }

    public static int pareto(int n) {
        return ((new Random().nextInt(100)) < 80) ? (((n/2 <= 1) ? 2 : pareto(n-1))) : n-1;
    }

}