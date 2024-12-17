package chapter8.var2;

import java.util.*;

public class TextProcessor {
    public List<Word> processText(String text) {
        // Разделяем текст на слова
        String[] words = text.split("\\s+");

        List<Word> vowelsWords = new ArrayList<>();

        for (String word : words) {
            Word w = new Word(word);
            if (w.startsWithVowel()) {
                vowelsWords.add(w);
            }
        }

        vowelsWords.sort((w1, w2) -> Character.compare(w1.getFirstConsonant(), w2.getFirstConsonant()));

        return vowelsWords;
    }
}
