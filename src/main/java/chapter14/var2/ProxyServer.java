package chapter14.var2;

import java.io.*;
import java.net.*;
import java.util.concurrent.*;
import java.util.logging.*;

public class ProxyServer {
    private static final int PROXY_PORT = 8080;
    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 9090;
    private static final Logger logger = Logger.getLogger(ProxyServer.class.getName());

    public static void main(String[] args) {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        try (ServerSocket serverSocket = new ServerSocket(PROXY_PORT)) {
            logger.info("Прокси-сервер запущен на порту " + PROXY_PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Клиент подключился: " + clientSocket.getInetAddress());
                threadPool.execute(new ClientHandler(clientSocket));
            }
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка запуска прокси-сервера", e);
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
            try (BufferedReader clientReader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                 PrintWriter clientWriter = new PrintWriter(clientSocket.getOutputStream(), true);
                 Socket serverSocket = new Socket(SERVER_HOST, SERVER_PORT);
                 BufferedReader serverReader = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));
                 PrintWriter serverWriter = new PrintWriter(serverSocket.getOutputStream(), true)) {

                logger.info("Перенаправление сообщений между клиентом и сервером...");

                String clientMessage;
                while ((clientMessage = clientReader.readLine()) != null) {
                    String modifiedMessage = MessageTransformer.encrypt(clientMessage);
                    logger.info("Перенаправление сообщения на сервер: " + modifiedMessage);
                    serverWriter.println(modifiedMessage);

                    String serverResponse = serverReader.readLine();
                    if (serverResponse == null) {
                        logger.info("Сервер закрыл соединение.");
                        break;
                    }
                    logger.info("Перенаправление ответа клиенту: " + serverResponse);
                    clientWriter.println(serverResponse);
                }
            } catch (IOException e) {
                logger.log(Level.WARNING, "Ошибка в связи клиент-сервер", e);
            } finally {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    logger.log(Level.WARNING, "Ошибка при закрытии клиентского сокета", e);
                }
            }
        }
    }
}