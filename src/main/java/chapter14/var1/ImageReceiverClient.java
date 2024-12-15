package chapter14.var1;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageReceiverClient {

    private static final Logger LOGGER = Logger.getLogger(ImageReceiverClient.class.getName());
    private static final String SERVER_ADDRESS = "localhost";
    private static final int PORT = 12345;
    private static final Path SAVE_PATH = Path.of("src/main/java/chapter14/received_image.jpg");

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, PORT);
             InputStream input = socket.getInputStream();
             OutputStream fileOutput = Files.newOutputStream(SAVE_PATH)) {

            LOGGER.info("Соединение с сервером установлено.");
            LOGGER.info("Начало загрузки файла...");

            byte[] buffer = new byte[1024];
            int bytesRead;

            while ((bytesRead = input.read(buffer)) != -1) {
                fileOutput.write(buffer, 0, bytesRead);
            }

            LOGGER.info(() -> "Файл успешно сохранён по пути: " + SAVE_PATH.toAbsolutePath());

        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Ошибка при получении файла", e);
        }
    }
}

