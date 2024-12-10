// Вариант: 8 Очиров Б. Б762-2

package chapter8.var3;

import java.util.*;

public class PhoneNumberSubstring {

    private static final String[] LETTERS = {
            "", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"
    };

    public static void main(String[] args) {
        // Пример телефонного номера и словаря
        String phoneNumber = "4663"; // Пример: 4663
        Set<String> dictionary = new HashSet<>(Arrays.asList("GOOD", "HOME", "HOOD", "MOM", "GONE"));

        // Находим максимальную подстроку, которая соответствует слову из словаря
        String result = findLongestWord(phoneNumber, dictionary);
        System.out.println("Longest word: " + result);
    }

    // Функция для нахождения самой длинной подстроки, которая является словом из словаря
    public static String findLongestWord(String phoneNumber, Set<String> dictionary) {
        int maxLength = 0;
        String longestWord = "";

        // Перебираем все возможные подстроки
        for (int i = 0; i < phoneNumber.length(); i++) {
            for (int j = i + 1; j <= phoneNumber.length(); j++) {
                String substring = phoneNumber.substring(i, j);
                Set<String> possibleWords = generateWords(substring);

                for (String word : possibleWords) {
                    if (dictionary.contains(word) && word.length() > maxLength) {
                        maxLength = word.length();
                        longestWord = word;
                    }
                }
            }
        }

        return longestWord;
    }

    // Генерация всех возможных слов для заданной подстроки цифр
    public static Set<String> generateWords(String digits) {
        Set<String> result = new HashSet<>();

        if (digits.isEmpty()) {
            return result;
        }

        // Получаем буквы для первой цифры
        char[] firstDigitLetters = LETTERS[digits.charAt(0) - '0'].toCharArray();

        // Если это единственная цифра, то просто возвращаем соответствующие буквы
        if (digits.length() == 1) {
            for (char c : firstDigitLetters) {
                result.add(String.valueOf(c));
            }
            return result;
        }

        // Генерируем слова для остальной части цифр
        Set<String> remainingWords = generateWords(digits.substring(1));

        // Для каждой буквы первой цифры добавляем возможные слова из оставшихся цифр
        for (char c : firstDigitLetters) {
            for (String word : remainingWords) {
                result.add(c + word);
            }
        }

        return result;
    }
}
