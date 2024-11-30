package org.example.chapter8.var2;

public class ConsonantUtils {
    // Метод для проверки, является ли символ согласной буквой
    public static boolean isConsonant(char ch) {
        return "бвгджзклмнпрстфхцчшщ".indexOf(ch) != -1;
    }
}
