// Вариант: 8 Очиров Б. Б762-2

package chapter10.var3;

import java.io.*;

public class RemoveExtraSpaces {
    public static void main(String[] args) {
        String inputFilePath = System.getProperty("testInputPath", "src/main/java/chapter10/var3/Input.java");
        String outputFilePath = System.getProperty("testOutputPath", "src/main/java/chapter10/var3/Output.java");

        File inputFile = new File(inputFilePath);
        File outputFile = new File(outputFilePath);

        try (
                BufferedReader reader = new BufferedReader(new FileReader(inputFile));
                BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmedLine = line.trim();
                String compactedLine = trimmedLine.replaceAll("\\s+", " ");

                if (compactedLine.contains("public class Input")) {
                    compactedLine = compactedLine.replace("public class Input", "class ProcessedInput");
                }

                writer.write(compactedLine);
                writer.newLine();
            }

            System.out.println("Обработка завершена. Результат записан в файл: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Ошибка при обработке файлов: " + e.getMessage());
        }
    }
}
