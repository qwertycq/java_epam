// Вариант: 8 Очиров Б. Б762-2

package chapter8.var1;

public class Task1 {
    public static void main(String[] args) {
        String input = "Пример (удаляемый текст) текста с (другим удаляемым) фрагментом.";

        String regex = "\\(.*?\\)";

        String result = input.replaceAll(regex, "");

        System.out.println(result);
    }
}
