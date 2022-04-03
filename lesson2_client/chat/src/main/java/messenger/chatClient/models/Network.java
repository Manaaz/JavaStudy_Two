package messenger.chatClient.models;

import javafx.application.Platform;
import messenger.chatClient.controllers.ChatController;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class Network {

    private static final String AUTH_CMD_PREFIX = "/auth"; // + login + password
    private static final String AUTHOK_CMD_PREFIX = "/authok"; // + username
    private static final String AUTHERR_CMD_PREFIX = "/autherr"; // + error message
    private static final String CLIENT_MSG_CMD_PREFIX = "/cMsg"; // + msg
    private static final String SERVER_MSG_CMD_PREFIX = "/sMsg"; // + msg
    private static final String PRIVATE_MSG_CMD_PREFIX = "/pMsg"; // + msg
    private static final String STOP_SERVER_CMD_PREFIX = "/stop";
    private static final String END_CLIENT_CMD_PREFIX = "/end";
    private static final String CONNECT_CLIENT = "/cntCl";
    private static final String DISCONNECT_CLIENT = "/dsCl";
    private static final String CLIENT_GET_ALL = "/allCL";
    private static final String CLIENT_RENAME = "/cntRnm";

    public static final String DEFAULT_HOST = "localhost";
    public static final int DEFAULT_PORT = 8186;
    private DataInputStream in;
    private DataOutputStream out;

    private final String host;
    private final int port;
    private String login;
    private String username;

    public Network(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public Network() {
        this.host = DEFAULT_HOST;
        this.port = DEFAULT_PORT;
    }

    public void connect() {
        try {
            Socket socket = new Socket(host, port);

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Соединение не установлено");
        }
    }

    public DataOutputStream getOut() {
        return out;
    }

    public void waitMessage(ChatController chatController) {

        Thread t = new Thread(() -> {
            try {

                while (true) {
                    String message = in.readUTF();

                    System.out.println(message);

                    if (message.startsWith(CLIENT_MSG_CMD_PREFIX)) {
                        //String[] parts = message.split("\\s+", 3);
                        //String sender = parts[1];
                        //String messageFromSender = parts[2];
                        //Platform.runLater(() -> chatController.appendMessage(String.format("%s: %s", sender, messageFromSender)));
                        Platform.runLater(() -> chatController.appendMessage(message));
                    } else if (message.startsWith(SERVER_MSG_CMD_PREFIX)) {
                        //String[] parts = message.split("\\s+", 2);
                        //String serverMessage = parts[1];
                        Platform.runLater(() -> chatController.appendServerMessage(message));
                    } else if (message.startsWith(DISCONNECT_CLIENT)) {
                        String[] parts = message.split("\\s+", 2);
                        String clientName = parts[1];
                        Platform.runLater(() ->chatController.userListRemoveClient(clientName));
                    } else if (message.startsWith(CLIENT_GET_ALL)) {
                        String[] parts = message.split("\\s+", 2);
                        String stringClients = parts[1];
                        Platform.runLater(() ->chatController.userListClearAndUpdateFromStringArray(stringClients));
                    } else if (message.startsWith(CLIENT_RENAME)) {
                        String[] parts = message.split("\\s+", 2);
                        username = parts[1];
                        Platform.runLater(() ->chatController.setUsernameTitle(username));
                    } else {
                        Platform.runLater(() -> chatController.appendMessage(message));
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        t.setDaemon(true);
        t.start();

    }

    public String sendAuthMessage(String login, String password) {
        try {
            out.writeUTF(String.format("%s %s %s", AUTH_CMD_PREFIX, login, password));
            String response = in.readUTF();
            if (response.startsWith(AUTHOK_CMD_PREFIX)) {
                this.username = response.split("\\s+", 2)[1];
                this.login = login;
                return null;
            } else {
                this.login = login;
                return response.split("\\s+", 2)[1];
            }

        } catch (IOException e) {
            e.printStackTrace();
            return e.getMessage();
        }

    }

    public String getUsername() {
        return username;
    }

    public void getAllClients() {
        sendMessage(CLIENT_GET_ALL);
    }

    public void sendMessage(String message) {
        try {
            if(!message.startsWith("/")) {
                message = String.format("%s %s", CLIENT_MSG_CMD_PREFIX, message);
            }
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Ошибка при отправке сообщения");
        }
    }

    public void sendPrivateMessage(String selectedRecipient, String message) {
        sendMessage(String.format("%s %s %s", PRIVATE_MSG_CMD_PREFIX, selectedRecipient, message));
    }

    public void sendRenameMessage(String name) {
        sendMessage(String.format("%s %s %s", CLIENT_RENAME, login, name));
    }
}
