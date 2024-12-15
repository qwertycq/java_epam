// Вариант: 8 Очиров Б. Б762-2

package chapter7;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.function.*;

public class WordCount {

    public static void main(String[] args) {
        List<String> wordsToCount = Arrays.asList("слово1", "слово2", "слово3");

        String text = readFile("src/main/java/chapter7/text.txt");

        Function<String, Integer> wordCounter = word -> countOccurrences(text, word);

        Consumer<String> resultPrinter = result -> System.out.print(result);

        List<String> results = new ArrayList<>();

        wordsToCount.forEach(word -> {
            int count = wordCounter.apply(word);
            results.add(word + "-" + count);
        });

        resultPrinter.accept("[" + String.join(", ", results) + "]");
    }

    private static String readFile(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get(fileName)), "UTF-8").toLowerCase();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static int countOccurrences(String text, String word) {
        return (int) Arrays.stream(text.split("\\s+"))
                .filter(w -> w.equals(word.toLowerCase()))
                .count();
    }
}
