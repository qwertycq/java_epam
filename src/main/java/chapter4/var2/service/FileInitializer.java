package chapter4.var2.service;

import chapter4.var2.transport.Locomotive;
import chapter4.var2.transport.PassengerCarriage;
import chapter4.var2.transport.Train;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileInitializer {
    public static Train initializeTrain(String filePath) throws IOException {
        Train train;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Чтение данных локомотива (первая строка файла)
            String locomotiveLine = reader.readLine();
            if (locomotiveLine == null) {
                throw new IOException("Файл не содержит данных локомотива");
            }
            String[] locomotiveParts = locomotiveLine.split(",");
            String fuelType = locomotiveParts[0].trim();
            int power = Integer.parseInt(locomotiveParts[1].trim());
            int weight = Integer.parseInt(locomotiveParts[2].trim());
            Locomotive locomotive = new Locomotive(fuelType, power, weight);

            // Инициализация поезда с локомотивом
            train = new Train(locomotive);

            // Чтение данных вагонов
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int comfortLevel = Integer.parseInt(parts[0].trim());
                int passengers = Integer.parseInt(parts[1].trim());
                int baggage = Integer.parseInt(parts[2].trim());
                train.addCarriage(new PassengerCarriage(comfortLevel, passengers, baggage));
            }
        }

        return train;
    }
}
