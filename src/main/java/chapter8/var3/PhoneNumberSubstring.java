// Вариант: 8 Очиров Б. Б762-2

package chapter8.var3;

import java.util.*;

public class PhoneNumberSubstring {

    private static final String[] LETTERS = {
            "", "", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"
    };

    public static void main(String[] args) {
        String phoneNumber = "4663";
        Set<String> dictionary = new HashSet<>(Arrays.asList("GOOD", "HOME", "HOOD", "MOM", "GONE"));

        String result = findLongestWord(phoneNumber, dictionary);
        System.out.println("Longest word: " + result);
    }

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

    public static Set<String> generateWords(String digits) {
        Set<String> result = new HashSet<>();

        if (digits.isEmpty()) {
            return result;
        }

        char[] firstDigitLetters = LETTERS[digits.charAt(0) - '0'].toCharArray();

        if (digits.length() == 1) {
            for (char c : firstDigitLetters) {
                result.add(String.valueOf(c));
            }
            return result;
        }

        Set<String> remainingWords = generateWords(digits.substring(1));

        for (char c : firstDigitLetters) {
            for (String word : remainingWords) {
                result.add(c + word);
            }
        }

        return result;
    }
}
