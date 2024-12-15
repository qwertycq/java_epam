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
        Path tempFile = Files.createTempFile("test", ".txt");
        Files.write(tempFile, "слово1 слово2 слово1 слово3 слово2 слово2".getBytes());

        List<String> wordsToCount = Arrays.asList("слово1", "слово2", "слово3");

        String text = new String(Files.readAllBytes(tempFile), "UTF-8").toLowerCase();

        Function<String, Integer> wordCounter = word -> countOccurrences(text, word);

        Map<String, Integer> expectedResults = new HashMap<>();
        expectedResults.put("слово1", 2);
        expectedResults.put("слово2", 3);
        expectedResults.put("слово3", 1);

        for (String word : wordsToCount) {
            int count = wordCounter.apply(word);
            assertEquals((int) expectedResults.get(word), count, "Ошибка для слова: " + word);
        }

        Files.delete(tempFile);
    }

    private int countOccurrences(String text, String word) {
        return (int) Arrays.stream(text.split("\\s+"))
                .filter(w -> w.equals(word.toLowerCase()))
                .count();
    }
}
