package chapter2.var1;

import java.util.Scanner;
import java.util.HashSet;

public class Task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        int firstUniqueDigitsNumber = -1;

        for (int num : numbers) {
            if (hasUniqueDigits(num)) {
                firstUniqueDigitsNumber = num;
                break;
            }
        }

        // Вывод результата
        if (firstUniqueDigitsNumber != -1) {
            System.out.println(firstUniqueDigitsNumber);
        }
    }

    public static boolean hasUniqueDigits(int num) {
        String numStr = String.valueOf(num);
        HashSet<Character> digitsSet = new HashSet<>();

        for (char digit : numStr.toCharArray()) {
            if (!digitsSet.add(digit)) {
                return false;
            }
        }
        return true;
    }
}
