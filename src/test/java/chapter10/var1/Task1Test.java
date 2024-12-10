package chapter10.var1;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {

    @Test
    void testCountWordFrequency() {
        String text = "Привет мир! Привет всем.";
        Map<String, Long> result = Task1.countWordFrequency(text);

        assertEquals(3, result.size());
        assertEquals(2, result.get("привет"));
        assertEquals(1, result.get("мир"));
        assertEquals(1, result.get("всем"));
    }

    @Test
    void testEmptyString() {
        String text = "";
        Map<String, Long> wordResult = Task1.countWordFrequency(text);
        Map<Character, Long> letterResult = Task1.countLetterFrequency(text);

        assertEquals(0, wordResult.size());
        assertEquals(0, letterResult.size());
    }

    @Test
    void testSpecialCharacters() {
        String text = "!!! @@@ ###";
        Map<String, Long> wordResult = Task1.countWordFrequency(text);
        Map<Character, Long> letterResult = Task1.countLetterFrequency(text);

        assertEquals(0, wordResult.size());
        assertEquals(0, letterResult.size());
    }
}
