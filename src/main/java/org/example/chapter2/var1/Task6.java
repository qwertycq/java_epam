package org.example.chapter2.var1;

import java.util.Scanner;

public class Task6 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        int firstIncreasingNumber = -1;

        for (int num : numbers) {
            if(isIncreasing(num)){
                firstIncreasingNumber = num;
                break;
            }
        }
        if (firstIncreasingNumber != -1) {
            System.out.println(firstIncreasingNumber);
        }
    }

    public static boolean isIncreasing(int num) {
        String numStr = String.valueOf(num);
        for (int i = 0; i < numStr.length() - 1; i++) {
            int currentDigit = Character.getNumericValue(numStr.charAt(i));
            int nextDigit = Character.getNumericValue(numStr.charAt(i + 1));
            if (currentDigit >= nextDigit) {
                return false;
            }
        }
        return true;
    }
}
