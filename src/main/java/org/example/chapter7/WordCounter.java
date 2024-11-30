// Вариант: 8 Очиров Б. Б762-2

package org.example.chapter7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class WordCounter {
    public static void main(String[] args) {
        String filePath = "src\\main\\java\\org\\example\\chapter7\\text.txt";

        try {
            String text = Files.readString(Path.of(filePath));
            Map<Character, Long> result = countCharacterOccurrences(text);

            String output = result.entrySet().stream()
                    .map(entry -> entry.getKey() + "-" + entry.getValue())
                    .collect(Collectors.joining(", ", "[", "]"));

            System.out.println(output);

        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }
    }

    public static Map<Character, Long> countCharacterOccurrences(String inputText) {
        return inputText.chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }
}
