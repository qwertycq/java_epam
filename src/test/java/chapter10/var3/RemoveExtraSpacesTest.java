package chapter10.var3;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class RemoveExtraSpacesTest {

    private File testInputFile;
    private File testOutputFile;

    @BeforeEach
    public void setUp() throws IOException {
        Path currentPath = Path.of(System.getProperty("user.dir"));

        testInputFile = new File(currentPath.toFile(), "TestInput.java");
        testOutputFile = new File(currentPath.toFile(), "TestOutput.java");

        String content = "public   class   Test {\n" +
                "    public static    void    main(String[] args) {  \n" +
                "        System.out.println(\"Hello,    World!\"); \n" +
                "    }\n" +
                "}  ";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(testInputFile))) {
            writer.write(content);
        }

        System.setProperty("testInputPath", testInputFile.getAbsolutePath());
        System.setProperty("testOutputPath", testOutputFile.getAbsolutePath());
    }

    @Test
    public void testRemoveExtraSpaces() throws IOException {
        RemoveExtraSpaces.main(new String[0]);

        String output = Files.readString(testOutputFile.toPath());

        String expectedOutput = "public class Test {\n" +
                "public static void main(String[] args) {\n" +
                "System.out.println(\"Hello, World!\");\n" +
                "}\n" +
                "}";

        assertEquals(expectedOutput.replaceAll("\\s+", " "), output.replaceAll("\\s+", " ").trim());
    }

    @AfterEach
    public void tearDown() {
        if (testInputFile.exists()) {
            testInputFile.delete();
        }
        if (testOutputFile.exists()) {
            testOutputFile.delete();
        }
    }
}
