package chapter8.var2;

import static org.junit.jupiter.api.Assertions.*;

import org.example.chapter8.var2.Word;
import org.junit.jupiter.api.Test;

public class WordTest {

    @Test
    public void testStartsWithVowel() {
        Word word1 = new Word("Атмосфера");
        Word word2 = new Word("Текст");

        // Проверяем, что слово начинается с гласной
        assertTrue(word1.startsWithVowel());
        assertFalse(word2.startsWithVowel());
    }

    @Test
    public void testGetFirstConsonant() {
        Word word1 = new Word("Атмосфера");
        Word word2 = new Word("Текст");

        // Проверяем первую согласную букву
        assertEquals('т', word1.getFirstConsonant());  // Ожидаем 'т', а не 'м'
        assertEquals('т', word2.getFirstConsonant());  // Первая согласная для "Текст" — 'т'
    }
}
