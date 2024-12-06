package server2;

public class Main {
    public static void main(String[] args) {

        ChatServer server = new ChatServer();
        server.start();

        ChatClient client = new ChatClient();
        client.start();
        ChatClient client2 = new ChatClient();
        client2.start();

    }
}

