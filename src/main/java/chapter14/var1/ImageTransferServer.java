package chapter14.var1;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageTransferServer {

    private static final Logger LOGGER = Logger.getLogger(ImageTransferServer.class.getName());
    private static final int PORT = 12345;
    private static final List<Path> IMAGE_PATHS = List.of(
            Path.of("src/main/java/chapter14/image1.png"),
            Path.of("src/main/java/chapter14/image2.png"),
            Path.of("src/main/java/chapter14/image3.png")
    );

    public static void main(String[] args) {
        if (IMAGE_PATHS.stream().noneMatch(Files::exists)) {
            LOGGER.severe("Ни один из указанных файлов не найден.");
            IMAGE_PATHS.forEach(path -> LOGGER.severe(() -> "Отсутствует файл: " + path.toAbsolutePath()));
            return;
        }

        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Scanner scanner = new Scanner(System.in)) {

            LOGGER.info("Сервер запущен. Выберите изображение для отправки:");

            for (int i = 0; i < IMAGE_PATHS.size(); i++) {
                final int index = i; // Ensure the variable is effectively final
                LOGGER.info(() -> (index + 1) + ". " + IMAGE_PATHS.get(index).toAbsolutePath());
            }

            System.out.print("Введите номер файла для отправки: ");
            int choice = scanner.nextInt() - 1;

            if (choice < 0 || choice >= IMAGE_PATHS.size() || !Files.exists(IMAGE_PATHS.get(choice))) {
                LOGGER.severe("Неверный выбор файла.");
                return;
            }

            Path selectedFile = IMAGE_PATHS.get(choice);
            LOGGER.info(() -> "Выбран файл: " + selectedFile.toAbsolutePath());

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                     OutputStream output = clientSocket.getOutputStream()) {

                    LOGGER.info(() -> "Клиент подключён: " + clientSocket.getInetAddress());

                    // Отправка файла
                    Files.copy(selectedFile, output);
                    LOGGER.info(() -> "Файл отправлен клиенту: " + clientSocket.getInetAddress());

                } catch (IOException e) {
                    LOGGER.log(Level.WARNING, "Ошибка при обработке клиента", e);
                }
            }
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Ошибка сервера", e);
        }
    }
}