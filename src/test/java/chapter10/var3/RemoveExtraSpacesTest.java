package chapter10.var3;

import org.example.chapter10.var3.RemoveExtraSpaces;
import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;

import static org.junit.jupiter.api.Assertions.*;

class RemoveExtraSpacesTest {

    private static final String INPUT_FILE_PATH = "test_input.java";
    private static final String OUTPUT_DIR_PATH = "test_output";
    private static final String OUTPUT_FILE_PATH = OUTPUT_DIR_PATH + "/output.java";

    @BeforeEach
    void setup() throws IOException {
        // Создание тестового входного файла
        Files.writeString(Path.of(INPUT_FILE_PATH), """
                public   class    Main {     
                    public static void     main(String[] args) {       
                        System.out.println(   "Hello, World!"   ); 
                    } 
                }
                """);

        // Удаление выходной директории перед тестами, если она существует
        Path outputDir = Path.of(OUTPUT_DIR_PATH);
        if (Files.exists(outputDir)) {
            Files.walk(outputDir)
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }

    @Test
    void testRemoveExtraSpaces() throws IOException {
        // Выполнение программы
        RemoveExtraSpaces.main(null);

        // Проверка, что файл создан
        File outputFile = new File(OUTPUT_FILE_PATH);
        assertTrue(outputFile.exists(), "Файл результата должен существовать");

        // Проверка содержимого выходного файла
        String expectedOutput = """
                public class Main {
                    public static void main(String[] args) {
                        System.out.println("Hello, World!");
                    }
                }
                """;
        String actualOutput = Files.readString(Path.of(OUTPUT_FILE_PATH));
        assertEquals(expectedOutput.strip(), actualOutput.strip(), "Содержимое файла должно соответствовать ожидаемому результату");
    }

    @AfterEach
    void cleanup() throws IOException {
        // Удаление тестовых файлов
        Files.deleteIfExists(Path.of(INPUT_FILE_PATH));
        Path outputDir = Path.of(OUTPUT_DIR_PATH);
        if (Files.exists(outputDir)) {
            Files.walk(outputDir)
                    .map(Path::toFile)
                    .forEach(File::delete);
        }
    }
}
