package chapter14.var2;

public class MessageTransformer {
    private static final int SHIFT = 3;

    public static String encrypt(String message) {
        return transform(message, SHIFT);
    }

    public static String decrypt(String message) {
        return transform(message, -SHIFT);
    }

    private static String transform(String message, int shift) {
        StringBuilder result = new StringBuilder();
        for (char c : message.toCharArray()) {
            if (Character.isLetter(c)) {
                char base = Character.isLowerCase(c) ? 'a' : 'A';
                c = (char) ((c - base + shift + 26) % 26 + base);
            }
            result.append(c);
        }
        return result.toString();
    }
}