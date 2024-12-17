// Вариант: 8 Очиров Б. Б762-2

package chapter8.var2;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        String text = "Пример текста, который начинается с гласных букв, а затем идет на согласные.";

        TextProcessor processor = new TextProcessor();

        List<Word> sortedWords = processor.processText(text);

        System.out.println("Отсортированные слова:");
        for (Word word : sortedWords) {
            System.out.println(word.getValue());
        }
    }
}
