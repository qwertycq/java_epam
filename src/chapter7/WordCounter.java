// Вариант: 8 Очиров Б. Б762-2

package chapter7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCounter {
    public static void main(String[] args) {
        String filePath = "src/chapter7/text.txt";

        try {
            String text = Files.readString(Path.of(filePath));

            // Функциональный интерфейс для подсчета символов
            Function<String, Map<Character, Long>> countCharacterOccurrences = (inputText) -> {
                return inputText.chars()  // Преобразуем строку в IntStream
                        .mapToObj(c -> (char) c)  // Преобразуем в поток символов
                        .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));  // Группируем и считаем
            };

            // Вызываем функцию подсчета
            Map<Character, Long> result = countCharacterOccurrences.apply(text);

            // Форматируем результат в строку
            String output = result.entrySet().stream()
                    .map(entry -> entry.getKey() + "-" + entry.getValue())
                    .collect(Collectors.joining(", ", "[", "]"));

            // Выводим результат
            System.out.println(output);

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }
}
