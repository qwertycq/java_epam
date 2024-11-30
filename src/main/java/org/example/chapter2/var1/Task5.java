package org.example.chapter2.var1;

import java.util.Scanner;

public class Task5 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();

        int[] numbers = new int[n];

        for (int i = 0; i < n; i++) {
            numbers[i] = scanner.nextInt();
        }

        int countAllEven = 0;
        int countEqualEvenOdd = 0;

        for (int num : numbers) {
            String numStr = String.valueOf(num);
            int evenCount = 0;
            int oddCount = 0;
            boolean allEven = true;

            for (char digit : numStr.toCharArray()) {
                int d = Character.getNumericValue(digit);

                if (d % 2 == 0) {
                    evenCount++;
                } else {
                    oddCount++;
                    allEven = false;
                }
            }

            if (allEven) {
                countAllEven++;
            }

            if (evenCount == oddCount) {
                countEqualEvenOdd++;
            }
        }
        System.out.println(countAllEven);
        System.out.println(countEqualEvenOdd);

    }
}