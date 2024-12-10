package chapter1.var2;

import java.util.Scanner;

public class Task3 {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.print("Числа, которые делятся на 3 или 9: ");
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 3 == 0 || array[i] % 9 == 0) {
                System.out.print(array[i] + " ");
            }
        }
        System.out.println();
    }
}
