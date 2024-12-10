package chapter2.var1;

import java.util.Scanner;

public class Task8 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] numbers = new int[n];
        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        int palindromeCount = 0;
        int secondPalindrome = -1;

        for (int num : numbers) {
            if (isPalindrome(num)) {
                palindromeCount++;
                if (palindromeCount == 2) {
                    secondPalindrome = num;
                }
            }
        }

        if (secondPalindrome != -1) {
            System.out.println(secondPalindrome);
        }
    }

    public static boolean isPalindrome(int num) {
        String numStr = String.valueOf(num);
        int len = numStr.length();
        for (int i = 0; i < len / 2; i++) {
            if (numStr.charAt(i) != numStr.charAt(len - i - 1)) {
                return false;
            }
        }
        return true;
    }
}
