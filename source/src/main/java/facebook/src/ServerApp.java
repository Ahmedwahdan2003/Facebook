package facebook.src;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerApp {

    public void startServer(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                handleClient(socket);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleClient(Socket socket) {
        new Thread(() -> {
            try {
                DataInputStream in = new DataInputStream(socket.getInputStream());
                while (true) {
                    String message = in.readUTF();

                    // Extract recipient name and handle the incoming message
                    String[] lines = message.split("\n");
                    String recipientName = lines[0].split(": ")[1];
                    handleIncomingMessage(recipientName, message);
                }
            } catch (IOException e) {
                // Handle socket closed or other IO issues
                e.printStackTrace();
            } finally {
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
    int roomId=1;
    private void handleIncomingMessage(String recipientName, String message) {
        // Perform actions based on the incoming message
        System.out.println("Room Id Is :" + ++roomId +"\n"+ message);
    }

    public static void main(String[] args) {
        // Example: Start the server on port 12345
        ServerApp serverApp = new ServerApp();
        serverApp.startServer(12345);
    }
}
