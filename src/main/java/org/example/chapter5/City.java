// Вариант: 8 Очиров Б. Б762-2

package org.example.chapter5;

public class City {

    private String name;

    public City(String name) {
        this.name = name;
    }

    public class Location {
        private String type;
        private String name;

        public Location(String type, String name) {
            this.type = type;
            this.name = name;
        }

        public String getType() {
            return type;
        }

        public String getName() {
            return name;
        }

        @Override
        public String toString() {
            return type + " " + name;
        }
    }

    public String getCityName() {
        return name;
    }

    public static void main(String[] args) {
        City city = new City("Улан-Удэ");

        Location avenue = city.new Location("Проспект", "Строителей");
        Location street = city.new Location("Улица", "Жердева");
        Location square = city.new Location("Площадь", "Советов");

        System.out.println("Город: " + city.getCityName());
        System.out.println("Местоположения:");
        System.out.println(avenue);
        System.out.println(street);
        System.out.println(square);
    }
}
