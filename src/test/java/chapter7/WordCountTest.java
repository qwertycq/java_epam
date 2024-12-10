package chapter7;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.nio.file.*;
import java.io.*;
import java.util.*;
import java.util.function.Function;

public class WordCountTest {

    @Test
    public void testWordCount() throws IOException {
        // Создаем временный файл для теста
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "слово1 слово2 слово1 слово3 слово2 слово2".getBytes());

        // Заданные слова для подсчета
        List<String> wordsToCount = Arrays.asList("слово1", "слово2", "слово3");

        // Чтение содержимого тестового файла
        String text = new String(Files.readAllBytes(tempFile), "UTF-8").toLowerCase();

        // Используем Function для подсчета вхождений
        Function<String, Integer> wordCounter = word -> countOccurrences(text, word);

        // Подсчитаем вхождения для каждого слова
        Map<String, Integer> expectedResults = new HashMap<>();
        expectedResults.put("слово1", 2);
        expectedResults.put("слово2", 3);
        expectedResults.put("слово3", 1);

        // Проверяем, что подсчитанные значения соответствуют ожидаемым
        for (String word : wordsToCount) {
            int count = wordCounter.apply(word);
            assertEquals((int) expectedResults.get(word), count, "Ошибка для слова: " + word);
        }

        // Удаляем временный файл после теста
        Files.delete(tempFile);
    }

    // Метод для подсчета количества вхождений слова в тексте
    private int countOccurrences(String text, String word) {
        return (int) Arrays.stream(text.split("\\s+"))
                .filter(w -> w.equals(word.toLowerCase()))
                .count();
    }
}
