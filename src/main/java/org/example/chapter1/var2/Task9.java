package org.example.chapter1.var2;

import java.util.ArrayList;
import java.util.Scanner;

public class Task9 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }

        for (int value : array) {
            if (isHappy(value)) {
                System.out.println(value + " ");
            }
        }
    }

    public static int sumDigits(int number) {
        int sum = 0;
        while (number > 0) {
            int digit = number % 10;
            sum += digit * digit;
            number /= 10;
        }
        return sum;
    }

    public static boolean isHappy(int number) {
        int value = number;
        ArrayList<Integer> seen_numbers = new ArrayList<>();
        while (value > 1 && !seen_numbers.contains(value)) {
            seen_numbers.add(value);
            value = sumDigits(value);
        }
        return value == 1;
    }
}
