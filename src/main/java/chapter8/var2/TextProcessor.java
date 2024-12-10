package chapter8.var2;

import java.util.*;

public class TextProcessor {
    // Метод для обработки текста и получения отсортированных слов
    public List<Word> processText(String text) {
        // Разделяем текст на слова
        String[] words = text.split("\\s+");

        // Список для хранения слов, начинающихся с гласных
        List<Word> vowelsWords = new ArrayList<>();

        // Перебираем слова и добавляем те, что начинаются с гласных
        for (String word : words) {
            Word w = new Word(word);
            if (w.startsWithVowel()) {
                vowelsWords.add(w);
            }
        }

        // Сортировка по первой согласной букве
        vowelsWords.sort((w1, w2) -> Character.compare(w1.getFirstConsonant(), w2.getFirstConsonant()));

        return vowelsWords;
    }
}
