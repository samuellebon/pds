package kan10.common;

import kan10.enums.BabyCategory;
import kan10.enums.Keywords;

import java.util.ArrayList;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnumUtils {

    public static ArrayList<String> getAll(Class aEnum) {
       return  (ArrayList<String>) Stream.of(aEnum.getEnumConstants()).map(c -> c.toString().toUpperCase()).collect(Collectors.toList());
    }

    public static int size(Class aEnum) {
        return getAll(aEnum).size();
    }

    public static void main(String[] args) {
        getAll(BabyCategory.class).forEach(System.out::println);
        System.out.println(size(Keywords.class));
    }

}