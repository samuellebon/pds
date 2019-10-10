package kan10.enums;

import kan10.common.EnumUtils;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public enum Keywords {

            TVs,
            TV_Accessories,
            DVD_Blu_ray_Players,
            Home_Audio_Theater,
            Cases_Accessories,
            Smartphones,
            Smart_Energy_Lighting,
            Smart_Home_Cameras_Security,
            iPad,
            Android_Tablets,
            Windows_Tablets,
            Laptops,
            Desktops_computer,
            PC_gaming,
            Computer_Accessories,
            Components,
            Monitors,
            Networking,
            Printers_Ink,
            Software,
            Wearable_tech,
            GPS_Navigation,
            Camera_Accesories,
            Headphones,
            Home_Speaker,
            iPod_MP3_Players,
            Video_Games,
            DVDs,
            CDs,
            Guitars,
            Keyboards,
            DJ_Equipment,
            eBooks,
            AudioBooks,
            TextBooks,
            Vinyl_Records,
            Appliances,
            Bath,
            Bedding,
            Furniture,
            Home_Decor,
            Kids_Bedding,
            Storage_Organization,
            Kids_Furniture,
            Kitchen_Dining,
            Mattresses,
            Patio_Garden,
            Rugs,
            Vacuums_Floor_Care,
            Window_Coverings,
            Women,
            Men,
            Kids,
            Shoes,
            Jewelry_Watches,
            Bags_Accessories,
            Diapering_Potty,
            Nursery_Decor,
            Toddler_Room,
            Strollers,
            Car_Seats,
            Play_TravelGear,
            Health_Safety,
            Baby_Clothing,
            Toddler_Clothing,
            Boys_Toys,
            Girls_Toys,
            Musical_Instruments,
            Outdoor_Play,
            Bikes_Ride_Ons,
            Gamecenter,
            Household_Essentials,
            Fresh_Flowers,
            Meal_Delivery_Services,
            Grocery_Pickup_Delivery,
            Pet_Supplies,
            Personal_Care,
            Bath_Body,
            Beauty,
            Pharmacy,
            Health,
            Wellness,
            Exercise_Machines,
            Fitness_Accessories,
            Functional_Training,
            Sports_Recovery,
            Strength_Training,
            Bikes,
            Baseball,
            Basketball,
            Football,
            Golf,
            Soccer,
            Racquet_Sports,
            Volleyball,
            Snow_Sports,
            Water_Sports,
            Oils_Fluids,
            Tools_Equipment,
            Tires,
            Batteries_Accessories,
            Interior,
            Exterior,
            Electronics,
            Lighting,
            Party_Ideas_Recipes,
            Costumes_Dress_Up,
            Christmas_Decor,
            Birthday,
            Wedding,;

    public static ArrayList<String> getAll() {
        return EnumUtils.getAll(Keywords.class);
    }

    public static int size() {
        return EnumUtils.size(Keywords.class);
    }

    public static String getOne() {
        return getAll().get(new Random().nextInt(size()));
    }

    public static ArrayList<String> getDistinct(int n) {
        return (ArrayList<String>) shuffle().stream().limit(n).distinct().collect(Collectors.toList());
    }

    private static ArrayList<String> shuffle() {
        ArrayList<String> list = getAll();
        Collections.shuffle(list);
        return list;
    }
}