// Вариант: 8 Очиров Б. Б762-2

package chapter10.var1;

import java.util.*;
import java.util.stream.Collectors;

public class Task1 {

    public static Map<String, Long> countWordFrequency(String text) {
        return Arrays.stream(text.toLowerCase().replaceAll("[^a-zа-яё\\s]", "").split("\\s+"))
                .filter(word -> !word.isEmpty())
                .collect(Collectors.groupingBy(word -> word, Collectors.counting()));
    }

    public static Map<Character, Long> countLetterFrequency(String text) {
        return text.toLowerCase().replaceAll("[^a-zа-яё]", "").chars()
                .mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите текст стихотворения Адама Мицкевича. Для завершения нажмите Enter дважды:");

        StringBuilder textBuilder = new StringBuilder();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.isEmpty()) break;
            textBuilder.append(line).append(" ");
        }
        scanner.close();

        String text = textBuilder.toString();

        Map<String, Long> wordFrequency = countWordFrequency(text);
        Map<Character, Long> letterFrequency = countLetterFrequency(text);

        System.out.println("\nЧастота повторяемости слов:");
        wordFrequency.forEach((word, freq) -> System.out.println(word + ": " + freq));

        System.out.println("\nЧастота повторяемости букв:");
        letterFrequency.forEach((letter, freq) -> System.out.println(letter + ": " + freq));
    }
}
