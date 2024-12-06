package server2;

import java.io.*;
import java.net.Socket;

public class ChatClient {
    private static final String HOST = "127.0.0.1";
    private static final int PORT = 12345;

    public void start() {
        try (
                Socket socket = new Socket(HOST, PORT);
                BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in))
        ) {
            System.out.println("[INFO] Подключено к серверу чата!");

            ClientReader reader = new ClientReader(socket);
            reader.start();

            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            String userMessage;

            while ((userMessage = userInput.readLine()) != null) {
                out.println(userMessage);
            }
        } catch (IOException e) {
            System.err.println("[ERROR] Ошибка клиента: " + e.getMessage());
        }
    }
}
