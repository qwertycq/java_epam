package chapter2.var1;

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        int n = input.nextInt();
        int[] a = new int[n];
        int[] lengths = new int[a.length];
        double averageDigitsLength = 0;

        for (int i = 0; i < a.length; i++) {
            a[i] = input.nextInt();
        }

        for (int i = 0; i < a.length; i++) {
            lengths[i] = getDigitsCount(a[i]);
            averageDigitsLength += lengths[i];
        }
        averageDigitsLength = averageDigitsLength / a.length;

        for (int i = 0; i < a.length; i++) {
            if (lengths[i] > averageDigitsLength) {
                System.out.print(a[i] + " ");
            }
        }
    }

    public static int getDigitsCount(int value) {
        int digitsCount = 0;
        while (value > 0) {
            digitsCount++;
            value /= 10;
        }
        return digitsCount;
    }
}