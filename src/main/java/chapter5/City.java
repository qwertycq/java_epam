// Вариант: 8 Очиров Б. Б762-2

package chapter5;

import java.util.ArrayList;
import java.util.List;

public class City {
    private String cityName;
    private List<Location> locations;

    public City(String cityName) {
        this.cityName = cityName;
        this.locations = new ArrayList<>();
    }

    public void addLocation(String type, String name) {
        locations.add(new Location(type, name));
    }

    public void displayLocations() {
        System.out.println("Город: " + cityName);
        System.out.println("Местоположения:");
        for (Location location : locations) {
            System.out.println(location);
        }
    }

    public class Location {
        private String type;
        private String name;

        private Location(String type, String name) {
            this.type = type;
            this.name = name;
        }

        @Override
        public String toString() {
            return type + " " + name;
        }
    }

    public static void main(String[] args) {
        City city = new City("Город");

        city.addLocation("Проспект", "1");
        city.addLocation("Улица", "2");
        city.addLocation("Площадь", "3");

        city.displayLocations();
    }

}
