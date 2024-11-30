package org.example.chapter2.var1;

import java.util.Scanner;

public class Task4 {

    public static int countUniqueDigits(int number) {
        int[] digitCount = new int[10];
        int uniqueDigits = 0;
        char[] digits = String.valueOf(number).toCharArray();

        for (char digit : digits) {
            int d = digit - '0';
            if (digitCount[d] == 0) {
                uniqueDigits++;
            }
            digitCount[d]++;
        }
        return uniqueDigits;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        int minUniqueDigits = Integer.MAX_VALUE;
        int result = -1;

        for (int num : numbers) {
            int uniqueCount = countUniqueDigits(num);

            if (uniqueCount < minUniqueDigits) {
                minUniqueDigits = uniqueCount;
                result = num;
            }
        }

        System.out.println(result);
    }
}

