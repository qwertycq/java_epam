package org.example.chapter1.var2;

import java.util.Scanner;

public class Task11 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        double[] array = new double[n];

        for (int i = 0; i < n; i++) {
            array[i] = scanner.nextDouble();
        }

        for (int i = 1; i < array.length - 1; i++) {
            if (halfSum(array[i - 1], array[i], array[i + 1])) {
                System.out.println(array[i]);
            }
        }
    }
    public static boolean halfSum(double left, double num, double right) {
        return num == (left + right) / 2;
    }
}
