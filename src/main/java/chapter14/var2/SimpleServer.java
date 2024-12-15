package chapter14.var2;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.logging.*;

public class SimpleServer {
    private static final int PORT = 9090;
    private static final Logger logger = Logger.getLogger(SimpleServer.class.getName());

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            logger.info("Сервер запущен на порту " + PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Клиент подключился: " + clientSocket.getInetAddress());
                threadPool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка запуска сервера", e);
        } finally {
            threadPool.shutdown();
        }
    }

    static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        public ClientHandler(Socket clientSocket) {
            this.clientSocket = clientSocket;
        }

        @Override
        public void run() {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true)) {

                String encryptedMessage = reader.readLine();
                if (encryptedMessage == null) {
                    logger.info("Клиент закрыл соединение");
                    return;
                }
                logger.info("Получено от прокси: " + encryptedMessage);

                String decryptedMessage = MessageTransformer.decrypt(encryptedMessage);
                logger.info("Расшифрованное сообщение: " + decryptedMessage);

                String response = "Сервер получил ваше сообщение: " + decryptedMessage;
                logger.info("Отправка ответа клиенту: " + response);
                writer.println(response);
            } catch (IOException e) {
                logger.log(Level.WARNING, "Ошибка в связи сервер-клиент", e);
            } finally {
                logger.info("Обработка запроса клиента завершена");
            }
        }
    }
}