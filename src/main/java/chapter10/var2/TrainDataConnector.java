package chapter10.var2;

import chapter10.var2.transport.Train;

import java.io.*;

public class TrainDataConnector {

    public static void saveTrainToFile(Train train, String filePath) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filePath))) {
            oos.writeObject(train);
            System.out.println("Данные поезда успешно сохранены в файл: " + filePath);
        }
    }

    public static Train loadTrainFromFile(String filePath) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filePath))) {
            System.out.println("Данные поезда загружены из файла: " + filePath);
            return (Train) ois.readObject();
        }
    }
}