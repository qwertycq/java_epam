package chapter10.var2;

import chapter10.var2.transport.Locomotive;
import chapter10.var2.transport.PassengerCarriage;
import chapter10.var2.transport.Train;

import java.io.*;

public class FileConnector {

    // Метод для загрузки поезда из файла
    public static Train loadTrainFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            Train train = (Train) ois.readObject();
            System.out.println("Поезд загружен из файла.");
            return train;
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException("Ошибка при загрузке поезда: " + e.getMessage());
        }
    }

    public static void saveTrainToFile(Train train, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(train);
            System.out.println("Поезд успешно сохранен в файл.");
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при сохранении поезда: " + e.getMessage());
        }
    }

    public static Train initializeTrainFromTextFile(String filePath) throws IOException {
        Train train = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String locomotiveLine = reader.readLine();
            if (locomotiveLine == null) {
                throw new IOException("Файл не содержит данных о локомотиве");
            }
            String[] locomotiveParts = locomotiveLine.split(",");
            String fuelType = locomotiveParts[0].trim();
            int power = Integer.parseInt(locomotiveParts[1].trim());
            int weight = Integer.parseInt(locomotiveParts[2].trim());
            Locomotive locomotive = new Locomotive(fuelType, power, weight);
            train = new Train(locomotive);

            System.out.println("Локомотив: " + locomotive);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int comfortLevel = Integer.parseInt(parts[0].trim());
                int passengers = Integer.parseInt(parts[1].trim());
                int baggage = Integer.parseInt(parts[2].trim());

                System.out.println("Создание вагона с комфортабельностью: " + comfortLevel +
                        ", пассажиров: " + passengers + ", багажа: " + baggage);

                train.addCarriage(new PassengerCarriage(comfortLevel, passengers, baggage));
            }
        } catch (IOException e) {
            throw new RuntimeException("Ошибка при инициализации поезда: " + e.getMessage());
        }

        return train;
    }
}
