package chapter9;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class NumberProcessorTest {

    @Test
    public void testValidFile() throws Exception {
        String testFile = "test_valid.txt";
        createTestFile(testFile, Arrays.asList(
                "12.34, en_US",
                "56.78, en_US",
                "90.12, en_US"
        ));

        List<Double> numbers = NumberProcessor.readAndParseFile(testFile);

        assertEquals(3, numbers.size());
        assertEquals(12.34 + 56.78 + 90.12, numbers.stream().mapToDouble(Double::doubleValue).sum(), 0.01);
        assertEquals((12.34 + 56.78 + 90.12) / 3, numbers.stream().mapToDouble(Double::doubleValue).average().orElse(0), 0.01);

        Files.delete(Paths.get(testFile)); // Очистка файла
    }

    @Test
    public void testFileNotFound() {
        Exception exception = assertThrows(CustomFileException.class, () -> {
            NumberProcessor.readAndParseFile("non_existing_file.txt");
        });

        assertTrue(exception.getMessage().contains("Файл не найден"));
    }

    @Test
    public void testInvalidNumberFormat() throws IOException {
        String testFile = "test_invalid_number.txt";
        createTestFile(testFile, Arrays.asList(
                "invalid_number, en_US",
                "100.0, en_US"
        ));

        Exception exception = assertThrows(CustomFileException.class, () -> {
            NumberProcessor.readAndParseFile(testFile);
        });

        assertTrue(exception.getMessage().contains("Ошибка парсинга числа"));

        Files.delete(Paths.get(testFile)); // Очистка файла
    }


    private void createTestFile(String filename, List<String> lines) throws IOException {
        Files.write(Paths.get(filename), lines);
    }
}
