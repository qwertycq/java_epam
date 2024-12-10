package chapter8.var3;

import org.junit.jupiter.api.Test;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

class PhoneNumberSubstringTest {

    @Test
    void testFindLongestWord_ValidInput() {
        String phoneNumber = "4663";
        Set<String> dictionary = Set.of("GOOD", "HOME", "HOOD", "MOM", "GONE");

        String result = PhoneNumberSubstring.findLongestWord(phoneNumber, dictionary);

        assertEquals("GOOD", result, "Самое длинное слово должно быть GOOD");
    }

    @Test
    void testFindLongestWord_NoMatchingWords() {
        String phoneNumber = "1234";
        Set<String> dictionary = Set.of("GOOD", "HOME", "HOOD");

        String result = PhoneNumberSubstring.findLongestWord(phoneNumber, dictionary);

        assertEquals("", result, "Если совпадений нет, результат должен быть пустой строкой");
    }

    @Test
    void testFindLongestWord_MultipleMatches() {
        String phoneNumber = "4663";
        Set<String> dictionary = Set.of("GOOD", "GONE");

        String result = PhoneNumberSubstring.findLongestWord(phoneNumber, dictionary);

        assertEquals("GOOD", result, "При нескольких совпадениях должно вернуться самое длинное слово");
    }

    @Test
    void testFindLongestWord_EmptyDictionary() {
        String phoneNumber = "4663";
        Set<String> dictionary = Set.of();

        String result = PhoneNumberSubstring.findLongestWord(phoneNumber, dictionary);

        assertEquals("", result, "Если словарь пуст, результат должен быть пустой строкой");
    }

    @Test
    void testFindLongestWord_EmptyPhoneNumber() {
        String phoneNumber = "";
        Set<String> dictionary = Set.of("GOOD", "HOME");

        String result = PhoneNumberSubstring.findLongestWord(phoneNumber, dictionary);

        assertEquals("", result, "Если номер телефона пуст, результат должен быть пустой строкой");
    }

    @Test
    void testGenerateWords_SingleDigit() {
        Set<String> words = PhoneNumberSubstring.generateWords("2");

        assertEquals(Set.of("A", "B", "C"), words, "Для цифры 2 должны генерироваться буквы A, B и C");
    }

    @Test
    void testGenerateWords_MultipleDigits() {
        Set<String> words = PhoneNumberSubstring.generateWords("23");

        assertTrue(words.contains("AD"), "Список должен содержать комбинацию AD");
        assertTrue(words.contains("AE"), "Список должен содержать комбинацию AE");
        assertTrue(words.contains("AF"), "Список должен содержать комбинацию AF");
        assertTrue(words.contains("BD"), "Список должен содержать комбинацию BD");
        assertTrue(words.contains("CF"), "Список должен содержать комбинацию CF");
        assertFalse(words.contains("ZZ"), "Список не должен содержать недопустимых комбинаций");
    }
}
