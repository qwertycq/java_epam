package chapter7;

import org.example.chapter7.WordCounter;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordCounterTest {

    @Test
    void testCountCharacterOccurrences() {
        // Входные данные
        String input = "hello world";

        // Ожидаемый результат
        Map<Character, Long> expected = Map.of(
                'h', 1L,
                'e', 1L,
                'l', 3L,
                'o', 2L,
                ' ', 1L,
                'w', 1L,
                'r', 1L,
                'd', 1L
        );

        // Вызов метода и проверка результата
        Map<Character, Long> actual = WordCounter.countCharacterOccurrences(input);
        assertEquals(expected, actual);
    }

    @Test
    void testCountCharacterOccurrencesEmptyString() {
        // Входные данные
        String input = "";

        // Ожидаемый результат
        Map<Character, Long> expected = Map.of();

        // Вызов метода и проверка результата
        Map<Character, Long> actual = WordCounter.countCharacterOccurrences(input);
        assertEquals(expected, actual);
    }

    @Test
    void testCountCharacterOccurrencesWithSpecialCharacters() {
        // Входные данные
        String input = "a!a!a!";

        // Ожидаемый результат
        Map<Character, Long> expected = Map.of(
                'a', 3L,
                '!', 3L
        );

        // Вызов метода и проверка результата
        Map<Character, Long> actual = WordCounter.countCharacterOccurrences(input);
        assertEquals(expected, actual);
    }
}
