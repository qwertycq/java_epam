package chapter10.var3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class RemoveExtraSpacesTest {

    private File inputFile;
    private File outputFile;

    @BeforeEach
    public void setUp() throws IOException {
        // Создание временного входного файла
        inputFile = Files.createTempFile("input", ".java").toFile();
        outputFile = new File("src/main/java/chapter10/var3/Output.java");

        String content = "public class Test {\n" +
                "    public static void main(String[] args) {  \n" +
                "        System.out.println(\"Hello, World!\"); \n" +
                "    }\n" +
                "}  ";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(inputFile))) {
            writer.write(content);
        }
    }

    @Test
    public void testRemoveExtraSpaces() throws IOException {
        // Запуск основного кода
        RemoveExtraSpaces.main(new String[0]);

        // Ожидаемый результат
        String expectedOutput = "public class Test {\n" +
                "    public static void main(String[] args) {\n" +
                "        System.out.println(\"Hello, World!\");\n" +
                "    }\n" +
                "}";

        // Сравнение результата с ожидаемым
        String output = new String(Files.readAllBytes(outputFile.toPath()));
        assertEquals(expectedOutput, output.trim());

        // Очистка временных файлов после теста
        Files.delete(inputFile.toPath());
        Files.delete(outputFile.toPath());
    }
}
