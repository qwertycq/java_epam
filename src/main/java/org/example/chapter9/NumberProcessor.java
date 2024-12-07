package org.example.chapter9;

import java.io.*;
import java.util.*;
import java.util.Locale;

public class NumberProcessor {
    private static final double MAX_VALUE = Double.MAX_VALUE;
    private static final double MIN_VALUE = -Double.MAX_VALUE;

    public static List<Double> readNumbersFromFile(String filePath) throws InvalidNumberException {
        List<Double> numbers = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    throw new InvalidNumberException("Invalid format: each line must contain a number and a locale.");
                }

                String numberPart = parts[0].trim();
                String localePart = parts[1].trim();

                try {
                    Locale locale = Locale.forLanguageTag(localePart);
                    double number = Double.parseDouble(numberPart.replace(',', '.')); // Normalize decimal separator
                    if (number < MIN_VALUE || number > MAX_VALUE) {
                        throw new InvalidNumberException("Number out of range: " + number);
                    }
                    numbers.add(number);
                } catch (NumberFormatException ex) {
                    throw new InvalidNumberException("Invalid number format: " + numberPart);
                }
            }
        } catch (FileNotFoundException ex) {
            throw new RuntimeException("File not found: " + filePath, ex);
        } catch (IOException ex) {
            throw new RuntimeException("Error reading file: " + filePath, ex);
        } catch (OutOfMemoryError ex) {
            throw new RuntimeException("Insufficient memory to process the file", ex);
        }

        return numbers;
    }

    public static double calculateSum(List<Double> numbers) {
        return numbers.stream().mapToDouble(Double::doubleValue).sum();
    }

    public static double calculateAverage(List<Double> numbers) {
        return numbers.isEmpty() ? 0 : calculateSum(numbers) / numbers.size();
    }
}
