package chapter8.var1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Task1Test {

    public static String removeSubstring(String input, String regex) {
        return input.replaceAll(regex, "");
    }

    @Test
    public void testRemoveTextBetweenParentheses() {
        String input = "Это пример (удаляемый текст) текста с (другим удаляемым) фрагментом.";
        String regex = "\\(.*?\\)";

        String result = removeSubstring(input, regex);

        assertEquals("Это пример  текста с  фрагментом.", result);
    }

    @Test
    public void testRemoveTextBetweenAsterisks() {
        String input = "Текст *удаляемый текст* внутри звездочек и *другой фрагмент*.";
        String regex = "\\*.*?\\*";

        String result = removeSubstring(input, regex);

        assertEquals("Текст  внутри звездочек и .", result);
    }

    @Test
    public void testRemoveTextBetweenBrackets() {
        String input = "Текст [удаляемый текст] внутри скобок и [другой фрагмент].";
        String regex = "\\[.*?\\]";

        String result = removeSubstring(input, regex);

        assertEquals("Текст  внутри скобок и .", result);
    }

    @Test
    public void testNoRemovalIfNoPattern() {
        String input = "Текст без скобок или звездочек";
        String regex = "\\(.*?\\)";

        String result = removeSubstring(input, regex);

        assertEquals("Текст без скобок или звездочек", result);
    }

    @Test
    public void testEmptyString() {
        String input = "";
        String regex = "\\(.*?\\)";

        String result = removeSubstring(input, regex);

        assertEquals("", result);
    }
}
