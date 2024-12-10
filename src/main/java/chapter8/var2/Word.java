package chapter8.var2;

public class Word {
    private String value;

    public Word(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    // Проверка, начинается ли слово с гласной буквы
    public boolean startsWithVowel() {
        return value.matches("^[аеёиоуыэюяАЕЁИОУЫЭЮЯ].*");
    }

    // Метод для нахождения первой согласной буквы
    public char getFirstConsonant() {
        for (char ch : value.toLowerCase().toCharArray()) {
            if (ConsonantUtils.isConsonant(ch)) {
                return ch;
            }
        }
        return ' '; // Если нет согласных букв
    }
}
