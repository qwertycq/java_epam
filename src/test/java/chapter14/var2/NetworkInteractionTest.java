package chapter14.var2;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class NetworkInteractionTest {

    @Test
    void testProxyMessageForwarding() throws IOException {
        // Mock server socket
        try (ServerSocket server = new ServerSocket(9090)) {
            Thread serverThread = new Thread(() -> {
                try (Socket client = server.accept();
                     BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                     PrintWriter out = new PrintWriter(client.getOutputStream(), true)) {

                    // Server reads and decrypts the message
                    String receivedEncrypted = in.readLine();
                    String receivedDecrypted = MessageTransformer.decrypt(receivedEncrypted);
                    assertEquals("HelloProxy", receivedDecrypted);

                    // Server sends response
                    String response = "Server received: " + receivedDecrypted;
                    out.println(response);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            serverThread.start();

            // Mock proxy socket
            try (Socket proxy = new Socket("localhost", 9090);
                 BufferedReader in = new BufferedReader(new InputStreamReader(proxy.getInputStream()));
                 PrintWriter out = new PrintWriter(proxy.getOutputStream(), true)) {

                String originalMessage = "HelloProxy";
                String encryptedMessage = MessageTransformer.encrypt(originalMessage);

                // Proxy sends encrypted message to server
                out.println(encryptedMessage);

                // Proxy reads response from server
                String response = in.readLine();
                assertEquals("Server received: HelloProxy", response);
            }
        }
    }
}
