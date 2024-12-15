package chapter10.var2;

import chapter10.var2.service.TrainService;
import chapter10.var2.transport.Carriage;
import chapter10.var2.transport.Train;

import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Train train = null;

        while (true) {
            System.out.println("=== Меню ===");
            System.out.println("1. Загрузить поезд из файла");
            System.out.println("2. Сохранить поезд в файл");
            System.out.println("3. Подсчитать общую численность пассажиров и багажа");
            System.out.println("4. Сортировать вагоны по уровню комфортности и вывести их");
            System.out.println("5. Найти вагоны по числу пассажиров");
            System.out.println("6. Вывести информацию о локомотиве");
            System.out.println("7. Выйти");
            System.out.print("Выберите опцию: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Введите путь к файлу для загрузки: ");
                    String filePath = scanner.next();
                    try {
                        train = FileConnector.loadTrainFromFile(filePath);
                        System.out.println("Поезд успешно загружен!");
                    } catch (IOException | ClassNotFoundException e) {
                        System.out.println("Ошибка при загрузке поезда: " + e.getMessage());
                    }
                }
                case 2 -> {
                    if (train != null) {
                        System.out.print("Введите путь к файлу для сохранения: ");
                        String filePath = scanner.next();
                        try {
                            FileConnector.saveTrainToFile(train, filePath);
                            System.out.println("Поезд успешно сохранен!");
                        } catch (IOException e) {
                            System.out.println("Ошибка при сохранении поезда: " + e.getMessage());
                        }
                    } else {
                        System.out.println("Поезд не загружен, сохранение невозможно.");
                    }
                }
                case 3 -> {
                    if (train != null) {
                        TrainService trainService = new TrainService();
                        System.out.println("Общее число пассажиров: " + trainService.calculateTotalPassengers(train));
                        System.out.println("Общее число багажа: " + trainService.calculateTotalBaggage(train));
                    } else {
                        System.out.println("Поезд не загружен.");
                    }
                }
                case 4 -> {
                    if (train != null) {
                        TrainService trainService = new TrainService();
                        trainService.sortCarriagesByComfort(train);
                        System.out.println("Вагоны отсортированы по уровню комфортности:");
                        for (Carriage carriage : train.getCarriages()) {
                            System.out.println("Уровень комфортности: " + carriage.getComfortLevel() +
                                    ", Пассажиры: " + carriage.getPassengerCapacity() +
                                    ", Багаж: " + carriage.getBaggageCapacity());
                        }
                    } else {
                        System.out.println("Поезд не загружен.");
                    }
                }
                case 5 -> {
                    if (train != null) {
                        TrainService trainService = new TrainService();
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
                    } else {
                        System.out.println("Поезд не загружен.");
                    }
                }
                case 6 -> {
                    if (train != null) {
                        System.out.println("Информация о локомотиве:");
                        System.out.println(train.getLocomotive());
                    } else {
                        System.out.println("Поезд не загружен.");
                    }
                }
                case 7 -> {
                    System.out.println("Выход из программы.");
                    return; // Выход из программы
                }
                default -> System.out.println("Неверный выбор!");
            }
        }
    }
}
