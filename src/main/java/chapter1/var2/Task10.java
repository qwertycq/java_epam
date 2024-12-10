package chapter1.var2;

import java.util.Scanner;

public class Task10 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        int n = scanner.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }

        for (int value : array) {
            if (isPalindrome(value)) {
                System.out.print(value + " ");
            }
        }
    }

    public static boolean isPalindrome(int value) {
        return value == reverseNumber(value);
    }

    public static int reverseNumber(int number) {
        int value = number;
        int total = 0;
        while (value > 0) {
            total = total * 10 + value % 10;
            value /= 10;
        }
        return total;
    }
}
