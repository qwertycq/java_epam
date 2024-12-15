package chapter14.var2;

import java.io.*;
import java.net.*;
import java.util.logging.*;

public class Client {
    private static final String PROXY_HOST = "localhost";
    private static final int PROXY_PORT = 8080;
    private static final Logger logger = Logger.getLogger(Client.class.getName());

    public static void main(String[] args) {
        try (Socket socket = new Socket(PROXY_HOST, PROXY_PORT);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))) {

            logger.info("Подключение к прокси-серверу установлено");

            System.out.print("Введите сообщение для отправки на прокси-сервер: ");
            String message = userInput.readLine();
            out.println(message);

            String response = in.readLine();
            logger.info("Ответ от сервера (через прокси): " + response);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Ошибка в связи клиент-сервер", e);
        }
    }
}