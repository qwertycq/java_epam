package chapter14.var1;

import org.junit.jupiter.api.*;
import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.logging.*;
import static org.junit.jupiter.api.Assertions.*;

public class ImageTransferTests {

    private static final int TEST_PORT = 12346;
    private static final Path TEST_IMAGE_PATH = Path.of("src/test/resources/test_image.png");
    private static final Path RECEIVED_IMAGE_PATH = Path.of("src/test/resources/received_image.png");

    @BeforeAll
    static void setup() throws IOException {
        Files.createDirectories(TEST_IMAGE_PATH.getParent());
        try (OutputStream os = Files.newOutputStream(TEST_IMAGE_PATH)) {
            os.write(new byte[]{1, 2, 3, 4, 5});
        }
    }

    @AfterAll
    static void cleanup() throws IOException {
        Files.deleteIfExists(TEST_IMAGE_PATH);
        Files.deleteIfExists(RECEIVED_IMAGE_PATH);
    }

    @Test
    void testServerClientFileTransfer() throws IOException, InterruptedException {
        Thread serverThread = new Thread(() -> {
            try (ServerSocket serverSocket = new ServerSocket(TEST_PORT)) {
                try (Socket clientSocket = serverSocket.accept();
                     OutputStream output = clientSocket.getOutputStream()) {
                    Files.copy(TEST_IMAGE_PATH, output);
                }
            } catch (IOException e) {
                fail("Ошибка на сервере: " + e.getMessage());
            }
        });

        serverThread.start();

        try (Socket socket = new Socket("localhost", TEST_PORT);
             InputStream input = socket.getInputStream();
             OutputStream fileOutput = Files.newOutputStream(RECEIVED_IMAGE_PATH)) {

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = input.read(buffer)) != -1) {
                fileOutput.write(buffer, 0, bytesRead);
            }
        }

        serverThread.join();

        assertTrue(Files.exists(RECEIVED_IMAGE_PATH), "Полученный файл не существует");
        assertArrayEquals(Files.readAllBytes(TEST_IMAGE_PATH), Files.readAllBytes(RECEIVED_IMAGE_PATH), "Содержимое файлов не совпадает");
    }

    @Test
    void testServerHandlesInvalidFile() {
        Logger.getLogger(ImageTransferServer.class.getName()).setLevel(Level.OFF);

        assertThrows(IOException.class, () -> {
            try (ServerSocket serverSocket = new ServerSocket(TEST_PORT)) {
                new Thread(() -> {
                    try (Socket clientSocket = new Socket("localhost", TEST_PORT);
                         InputStream input = clientSocket.getInputStream()) {
                    } catch (IOException ignored) {
                    }
                }).start();

                try (Socket clientSocket = serverSocket.accept();
                     OutputStream output = clientSocket.getOutputStream()) {
                    Files.copy(Path.of("non_existent_file.png"), output);
                }
            }
        });
    }
}
