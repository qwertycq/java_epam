package chapter8.var2;

import static org.junit.jupiter.api.Assertions.*;

import org.example.chapter8.var2.TextProcessor;
import org.example.chapter8.var2.Word;
import org.junit.jupiter.api.Test;
import java.util.List;

public class TextProcessorTest {

    @Test
    public void testProcessText() {
        String text = "Это пример текста, который начинается с гласных букв.";
        TextProcessor processor = new TextProcessor();

        // Получаем отсортированные слова
        List<Word> result = processor.processText(text);

        // Проверяем, что результат не пуст
        assertNotNull(result);
        assertFalse(result.isEmpty());

        // Проверяем, что все слова начинаются с гласных
        for (Word word : result) {
            assertTrue(word.startsWithVowel(), "Word does not start with a vowel: " + word.getValue());
        }

        // Проверяем, что слова отсортированы по первой согласной
        for (int i = 0; i < result.size() - 1; i++) {
            char firstConsonant1 = result.get(i).getFirstConsonant();
            char firstConsonant2 = result.get(i + 1).getFirstConsonant();
            // Сравниваем первую согласную букву
            if (firstConsonant1 != ' ' && firstConsonant2 != ' ') {
                assertTrue(firstConsonant1 <= firstConsonant2, "Words are not sorted properly by first consonant");
            }
        }
    }

    @Test
    public void testProcessEmptyText() {
        String text = "";
        TextProcessor processor = new TextProcessor();

        // Проверяем, что с пустым текстом возвращается пустой список
        List<Word> result = processor.processText(text);
        assertNotNull(result);
        assertTrue(result.isEmpty());
    }
}
