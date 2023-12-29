package facebook.src;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientApp extends Application {

    private String username;


    public void setUsername(String username) {
        this.username = username;
    }


    public String getUsername() {
        return this.username;
    }

    private DataOutputStream out;


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
    }


    public void startClient(String serverAddress, int serverPort) {
        try {
            Socket socket = new Socket(serverAddress, serverPort);

            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                try {
                    DataInputStream in = new DataInputStream(socket.getInputStream());
                    while (true) {
                        String message = in.readUTF();
                        System.out.println("Received message from server: " + message);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void sendMessage(String recipientName, String message, String time) {
        if (out != null && !message.isEmpty()) {
            try {
                out.writeUTF("Massage sent From :  " + username + " \nTo " + recipientName + "\nmassage content : "
                        + message + "\ntime : " + time);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public void sendMessageToServer(String message, String recipientName, String time) {
        new Thread(() -> {
            try {
                String serverAddress = "Localhost";
                int serverPort = 12345;
                Socket socket = new Socket(serverAddress, serverPort);

                DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
                dos.writeUTF(message);
                dos.writeUTF(recipientName);
                dos.writeUTF(time);

                socket.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }).start();
    }


    @Override
    public void stop() {
        Platform.exit();
    }
}
