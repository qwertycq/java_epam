package chapter4.var2.main;

import chapter4.var2.service.FileInitializer;
import chapter4.var2.service.TrainService;
import chapter4.var2.transport.Carriage;
import chapter4.var2.transport.Train;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try {
            Train train = FileInitializer.initializeTrain("C:\\Users\\Bator\\Desktop\\java_projects\\java_emap\\src\\chapter4\\var2\\train_data.txt");
            TrainService trainService = new TrainService();
            Scanner scanner = new Scanner(System.in);

            System.out.println("=== Меню ===");
            System.out.println("1. Подсчитать общую численность пассажиров и багажа");
            System.out.println("2. Сортировать вагоны по уровню комфортности и вывести их");
            System.out.println("3. Найти вагоны по числу пассажиров");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.println("Общее число пассажиров: " + trainService.calculateTotalPassengers(train));
                    System.out.println("Общее число багажа: " + trainService.calculateTotalBaggage(train));
                }
                case 2 -> {
                    trainService.sortCarriagesByComfort(train);
                    System.out.println("Вагоны отсортированы по уровню комфортности:");
                    for (Carriage carriage : train.getCarriages()) {
                        System.out.println("Уровень комфортности: " + carriage.getComfortLevel() +
                                ", Пассажиры: " + carriage.getPassengerCapacity() +
                                ", Багаж: " + carriage.getBaggageCapacity());
                    }
                }
                case 3 -> {
                    System.out.print("Введите минимальное число пассажиров: ");
                    int min = scanner.nextInt();
                    System.out.print("Введите максимальное число пассажиров: ");
                    int max = scanner.nextInt();
                    var foundCarriages = trainService.findCarriagesByPassengerRange(train, min, max);
                    System.out.println("Найдено вагонов: " + foundCarriages.size());
                    for (Carriage carriage : foundCarriages) {
                        System.out.println("Уровень комфортности: " + carriage.getComfortLevel() +
                                ", Пассажиры: " + carriage.getPassengerCapacity() +
                                ", Багаж: " + carriage.getBaggageCapacity());
                    }
                }
                default -> System.out.println("Неверный выбор!");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
