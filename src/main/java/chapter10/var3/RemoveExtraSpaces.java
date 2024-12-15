// Вариант: 8 Очиров Б. Б762-2

package chapter10.var3;

import java.io.*;

public class RemoveExtraSpaces {
    public static void main(String[] args) {
        // Исходный файл (входной) и директория для результата
        File inputFile = new File("src/main/java/chapter10/var3/Input.java");
        File outputDirectory = new File("src/main/java/chapter10/var3/");
        File outputFile = new File(outputDirectory, "Output.java");

        // Создание директории, если она не существует
        if (!outputDirectory.exists()) {
            if (outputDirectory.mkdir()) {
                System.out.println("Создана директория: " + outputDirectory.getAbsolutePath());
            } else {
                System.out.println("Не удалось создать директорию.");
                return;
            }
        }

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim();
                String compactedLine = trimmedLine.replaceAll("\\s+", " ");
                writer.write(compactedLine);
                writer.newLine();
            }

            System.out.println("Обработка завершена. Результат записан в файл: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файлов: " + e.getMessage());
        }
    }
}
