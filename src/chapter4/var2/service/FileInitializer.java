package chapter4.var2.service;

import chapter4.var2.transport.PassengerCarriage;
import chapter4.var2.transport.Train;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileInitializer {
    public static Train initializeTrain(String filePath) throws IOException {
        Train train = new Train();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
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
