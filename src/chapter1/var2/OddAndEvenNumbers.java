package chapter1.var2;

import java.util.Scanner;

public class OddAndEvenNumbers {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        int[] array = new int[n];

        for (int i = 0; i < array.length; i++) {
            array[i] = scanner.nextInt();
        }

        printEvenAndOddNumbers(array);
        printMinMax(array);
        printDivision3And9(array);
        printDivision5And7(array);
        printUniqueNumbers(array);
        printPrimeNumbers(array);
        printReverseNumbers(array);
    }

    private static void printEvenAndOddNumbers(int[] array) {
        System.out.print("Четные числа: ");
        boolean hasEven = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 == 0) {
                System.out.print(array[i] + " ");
                hasEven = true;
            }
        }
        if (!hasEven) {
            System.out.print("Нет четных чисел");
        }
        System.out.println();

        System.out.print("Нечетные числа: ");
        boolean hasOdd = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 2 != 0) {
                System.out.print(array[i] + " ");
                hasOdd = true;
            }
        }
        if (!hasOdd) {
            System.out.print("Нет нечетных чисел");
        }
        System.out.println();
    }

    private static void printMinMax(int[] array) {
        int min = array[0];
        int max = array[0];

        for (int i = 1; i < array.length; i++) {
            if (array[i] < min) {
                min = array[i];
            }
            if (array[i] > max) {
                max = array[i];
            }
        }

        System.out.println("Мин и макс: " + min + " " + max);
    }

    private static void printDivision3And9(int[] array) {
        System.out.print("Числа, которые делятся на 3 или 9: ");
        boolean hasDivisibleBy3Or9 = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 3 == 0 || array[i] % 9 == 0) {
                System.out.print(array[i] + " ");
                hasDivisibleBy3Or9 = true;
            }
        }
        if (!hasDivisibleBy3Or9) {
            System.out.print("Нет чисел, делящихся на 3 или 9");
        }
        System.out.println();
    }

    private static void printDivision5And7(int[] array) {
        System.out.print("Числа, которые делятся на 5 или 7: ");
        boolean hasDivisibleBy5Or7 = false;
        for (int i = 0; i < array.length; i++) {
            if (array[i] % 5 == 0 || array[i] % 7 == 0) {
                System.out.print(array[i] + " ");
                hasDivisibleBy5Or7 = true;
            }
        }
        if (!hasDivisibleBy5Or7) {
            System.out.print("Нет чисел, делящихся на 5 или 7");
        }
        System.out.println();
    }

    private static void printUniqueNumbers(int[] array) {
        System.out.print("Числа с уникальными цифрами (трехзначные): ");
        boolean hasUniqueDigits = false;
        for (int i : array) {
            if (i >= 100 && i < 1000) {
                int hundreds = i / 100;
                int tens = (i / 10) % 10;
                int units = i % 10;

                if (hundreds != tens && hundreds != units && tens != units) {
                    System.out.print(i + " ");
                    hasUniqueDigits = true;
                }
            }
        }
        if (!hasUniqueDigits) {
            System.out.print("Нет трехзначных чисел с уникальными цифрами");
        }
        System.out.println();
    }

    private static void printPrimeNumbers(int[] array) {
        System.out.print("Простые числа: ");
        boolean hasPrimeNumbers = false;
        for (int i : array) {
            if (isPrime(i)) {
                System.out.print(i + " ");
                hasPrimeNumbers = true;
            }
        }
        if (!hasPrimeNumbers) {
            System.out.print("Нет простых чисел");
        }
        System.out.println();
    }

    private static boolean isPrime(int num) {
        if (num <= 1) {
            return false;
        }
        if (num == 2) {
            return true;
        }
        if (num % 2 == 0) {
            return false;
        }

        for (int i = 3; i <= Math.sqrt(num); i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }

    private static void printReverseNumbers(int[] array) {
        System.out.print("Числа в обратном порядке: ");
        for (int i = array.length-1; i >= 0; i--) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }




}
