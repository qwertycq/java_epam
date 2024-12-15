// Вариант: 8 Очиров Б. Б762-2

package chapter9;

import java.io.*;
import java.util.*;
import java.text.*;

public class NumberProcessor {
    public static void main(String[] args) {
        String filename = "src/main/java/chapter9/numbers.txt";
        try {
            List<Double> numbers = readAndParseFile(filename);
            double sum = 0;
            for (Double num : numbers) {
                sum += num;
            }
            double average = numbers.size() > 0 ? sum / numbers.size() : 0;
            System.out.println("Сумма: " + sum);
            System.out.println("Среднее: " + average);
        } catch (CustomFileException e) {
            System.err.println("Ошибка: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            System.err.println("Ошибка памяти: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Неизвестная ошибка: " + e.getMessage());
        }
    }

    static List<Double> readAndParseFile(String filename) throws CustomFileException {
        List<Double> numbers = new ArrayList<>();
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader(filename));
            String line;
            Locale currentLocale = Locale.getDefault();

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    throw new CustomFileException("Некорректный формат записи в файле: " + line);
                }

                String numberStr = parts[0].trim();
                String localeStr = parts[1].trim();

                try {
                    currentLocale = new Locale(localeStr);
                } catch (Exception e) {
                    throw new CustomFileException("Некорректная локаль: " + localeStr);
                }

                try {
                    NumberFormat format = NumberFormat.getInstance(currentLocale);
                    Number number = format.parse(numberStr);
                    if (number.doubleValue() < Double.MIN_VALUE || number.doubleValue() > Double.MAX_VALUE) {
                        throw new CustomFileException("Число выходит за допустимые пределы: " + numberStr);
                    }
                    numbers.add(number.doubleValue());
                } catch (ParseException e) {
                    throw new CustomFileException("Ошибка парсинга числа: " + numberStr);
                }
            }
        } catch (FileNotFoundException e) {
            throw new CustomFileException("Файл не найден: " + filename);
        } catch (IOException e) {
            throw new CustomFileException("Ошибка чтения файла: " + e.getMessage());
        } catch (OutOfMemoryError e) {
            throw e;
        } finally {
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                System.err.println("Ошибка при закрытии файла: " + e.getMessage());
            }
        }

        return numbers;
    }
}
