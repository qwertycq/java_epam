// Вариант: 8 Очиров Б. Б762-2

package chapter7;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;

public class WordCount {

    public static void main(String[] args) {
        // Заданные слова для подсчета
        List<String> wordsToCount = Arrays.asList("слово1", "слово2", "слово3");

        // Чтение содержимого файла
        String text = readFile("src/main/java/chapter7/text.txt");

        // Используем функциональный интерфейс для подсчета вхождений каждого слова
        Function<String, Integer> wordCounter = word -> countOccurrences(text, word);

        // Используем Consumer для вывода результатов
        Consumer<String> resultPrinter = result -> System.out.print(result);

        // Создаем список для хранения результатов
        List<String> results = new ArrayList<>();

        // Для каждого слова подсчитываем количество вхождений и добавляем в список
        wordsToCount.forEach(word -> {
            int count = wordCounter.apply(word);
            results.add(word + "-" + count);
        });

        // Выводим результат в нужном формате
        resultPrinter.accept("[" + String.join(", ", results) + "]");
    }

    // Метод для чтения содержимого файла
    private static String readFile(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)), "UTF-8").toLowerCase();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    // Метод для подсчета количества вхождений слова в тексте
    public static int countOccurrences(String text, String word) {
        return (int) Arrays.stream(text.split("\\s+"))
                .filter(w -> w.equals(word.toLowerCase()))
                .count();
    }
}
