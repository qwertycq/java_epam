// Очиров Б. Б762-2, 8 Вариант

package org.example.chapter3.var1;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Car> cars = new ArrayList<>();

        cars.add(new Car(1, "Toyota", "Camry", 2019, "Белый", 20000, "A123BC"));
        cars.add(new Car(2, "BMW", "X5", 2017, "Черный", 35000, "B456DE"));
        cars.add(new Car(3, "Audi", "A6", 2010, "Синий", 18000, "C789FG"));
        cars.add(new Car(4, "Toyota", "Corolla", 2012, "Серый", 15000, "D101GH"));
        cars.add(new Car(5, "Mercedes", "E-Class", 2020, "Красный", 50000, "E202IJ"));

        String targetBrand = "Toyota";

        System.out.println("Список автомобилей марки " + targetBrand + ":");
        for (Car car : cars) {
            if (car.getBrand().equalsIgnoreCase(targetBrand)) {
                System.out.println(car);
            }
        }

        String targetModel = "A6";
        int n = 10;
        System.out.println("\nСписок автомобилей модели " + targetModel + ", которые эксплуатируются больше " + n + " лет:");
        for (Car car : cars) {
            if (car.getModel().equalsIgnoreCase(targetModel) && car.getCarAge() > n) {
                System.out.println(car);
            }
        }

        int targetYear = 2015;
        double minPrice = 25000;
        System.out.println("\nСписок автомобилей " + targetYear + " года выпуска, цена которых больше " + minPrice + ":");
        for (Car car : cars) {
            if (car.getYear() == targetYear && car.getPrice() > minPrice) {
                System.out.println(car);
            }
        }
    }
}
