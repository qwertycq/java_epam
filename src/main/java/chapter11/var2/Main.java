// Вариант: 8 Очиров Б. Б762-2

package chapter11.var2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        String inputFile = "src/main/java/chapter11/var2/input.txt";

        try {
            List<String> lines = Files.readAllLines(Paths.get(inputFile));

            List<ObjectProcessor.ObjectInfo> objects = lines.stream()
                    .map(line -> {
                        String[] parts = line.split(",");
                        String name = parts[0].trim();
                        int code = Integer.parseInt(parts[1].trim());
                        return new ObjectProcessor.ObjectInfo(name, code);
                    })
                    .collect(Collectors.toList());

            List<ObjectProcessor.ObjectInfo> processedObjects = ObjectProcessor.processObjects(objects);

            System.out.println("Результат обработки:");
            processedObjects.forEach(System.out::println);

        } catch (IOException e) {
            System.err.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("Ошибка в формате данных: " + e.getMessage());
        }
    }
}
