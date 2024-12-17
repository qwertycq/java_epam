package chapter8.var2;

public class Word {
    private String value;

    public Word(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public boolean startsWithVowel() {
        return value.matches("^[аеёиоуыэюяАЕЁИОУЫЭЮЯ].*");
    }

    public char getFirstConsonant() {
        for (char ch : value.toLowerCase().toCharArray()) {
            if (ConsonantUtils.isConsonant(ch)) {
                return ch;
            }
        }
        return ' ';
    }
}
