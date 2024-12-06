
package server2;
import java.io.*;
import java.net.Socket;

public class ClientHandler extends Thread {
    private final Socket socket;
    private final ChatServer server;
    private PrintWriter out;

    public ClientHandler(Socket socket, ChatServer server) {
        this.socket = socket;
        this.server = server;
    }

    @Override
    public void run() {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))
        ) {
            out = new PrintWriter(socket.getOutputStream(), true);
            String message;
            while ((message = in.readLine()) != null) {
                System.out.println("[MESSAGE] " + message);
                server.broadcastMessage(message, this);
            }
        } catch (IOException e) {
            System.err.println("[ERROR] Ошибка с клиентом: " + e.getMessage());
        } finally {
            server.removeClient(this);
            closeConnection();
        }
    }

    public void sendMessage(String message) {
        if (out != null) {
            out.println(message);
        }
    }

    private void closeConnection() {
        try {
            socket.close();
        } catch (IOException e) {
            System.err.println("[ERROR] Ошибка при закрытии соединения: " + e.getMessage());
        }
    }
}
