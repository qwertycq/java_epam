package chapter1.var2;

import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Task7 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        Integer[] array = new Integer[n];

        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }

        Arrays.sort(array);

        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();

        Arrays.sort(array, Collections.reverseOrder());

        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}
