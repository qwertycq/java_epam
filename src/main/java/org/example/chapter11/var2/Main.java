package org.example.chapter11.var2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String inputFile = "src/main/java/org/example/chapter11/var2/input.txt";

        try {
            // Чтение данных из файла
            List<String> lines = Files.readAllLines(Paths.get(inputFile));

            // Преобразование строк в список объектов ObjectInfo
            List<ObjectProcessor.ObjectInfo> objects = lines.stream()
                    .map(line -> {
                        String[] parts = line.split(","); // Разделяем строку по запятой
                        String name = parts[0].trim();    // Наименование объекта
                        int code = Integer.parseInt(parts[1].trim()); // Шифр
                        return new ObjectProcessor.ObjectInfo(name, code);
                    })
                    .collect(Collectors.toList());

            // Обработка данных: сортировка и удаление дубликатов
            List<ObjectProcessor.ObjectInfo> processedObjects = ObjectProcessor.processObjects(objects);

            // Вывод результата
            System.out.println("Результат обработки:");
            processedObjects.forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Ошибка в формате данных: " + e.getMessage());
        }
    }
}
