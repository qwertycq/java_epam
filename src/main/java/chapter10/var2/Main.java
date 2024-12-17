// Вариант: 8 Очиров Б. Б762-2

package chapter10.var2;

import chapter10.var2.service.FileInitializer;
import chapter10.var2.service.TrainService;
import chapter10.var2.transport.Carriage;
import chapter10.var2.transport.Train;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Train train = null;
        TrainService trainService = new TrainService();
        String filePath = "src/main/java/chapter10/var2/train_data.ser";

        try {
            // Загружаем поезд из файла или инициализируем новый
            System.out.println("1. Загрузить поезд из файла");
            System.out.println("2. Создать новый поезд из файла данных");
            int choice = scanner.nextInt();

            if (choice == 1) {
                train = TrainDataConnector.loadTrainFromFile(filePath);
            } else if (choice == 2) {
                train = FileInitializer.initializeTrain("src/main/java/chapter10/var2/train_data.ser");
                TrainDataConnector.saveTrainToFile(train, filePath);
            } else {
                System.out.println("Неверный выбор!");
                return;
            }

            // Меню
            System.out.println("=== Меню ===");
            System.out.println("1. Подсчитать общую численность пассажиров и багажа");
            System.out.println("2. Сортировать вагоны по уровню комфортности и вывести их");
            System.out.println("3. Найти вагоны по числу пассажиров");
            System.out.println("4. Вывести информацию о локомотиве");
            System.out.println("5. Сохранить поезд в файл");

            System.out.print("Выберите опцию: ");
            int option = scanner.nextInt();

            switch (option) {
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
                }
                case 4 -> System.out.println(train.getLocomotive());
                case 5 -> TrainDataConnector.saveTrainToFile(train, filePath);
                default -> System.out.println("Неверный выбор!");
            }
        } catch (Exception e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
