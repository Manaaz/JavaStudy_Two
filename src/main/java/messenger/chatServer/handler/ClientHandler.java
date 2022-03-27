package messenger.chatServer.handler;

import messenger.chatServer.MyServer;
import messenger.chatServer.authentication.AuthenticationService;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ClientHandler {
    private static final String AUTH_CMD_PREFIX = "/auth"; // + login + password
    private static final String AUTHOK_CMD_PREFIX = "/authok"; // + username
    private static final String AUTHERR_CMD_PREFIX = "/autherr"; // + error message
    private static final String CLIENT_MSG_CMD_PREFIX = "/cMsg"; // + msg
    private static final String SERVER_MSG_CMD_PREFIX = "/sMsg"; // + msg
    private static final String PRIVATE_MSG_CMD_PREFIX = "/pMsg"; // + msg
    private static final String STOP_SERVER_CMD_PREFIX = "/stop";
    private static final String END_CLIENT_CMD_PREFIX = "/end";
    private static final String CLIENT_CONNECT = "/cntCl";
    private static final String CLIENT_DISCONNECT = "/dsCl";
    private static final String CLIENT_GET_ALL = "/allCL";

    private MyServer myServer;
    private Socket clientSocket;
    private DataOutputStream out;
    private DataInputStream in;
    private String username;

    public ClientHandler(MyServer myServer, Socket socket) {

        this.myServer = myServer;
        clientSocket = socket;
    }

    public void handle() throws IOException {
        out = new DataOutputStream(clientSocket.getOutputStream());
        in = new DataInputStream(clientSocket.getInputStream());

        new Thread(() -> {
            try {
                authentication();
                readMessage();
                myServer.broadcastMessage(String.format("%s %s", CLIENT_CONNECT, username), this, true);
            } catch (IOException e) {
                //e.printStackTrace();
                try {
                    System.out.println(String.format("Пользователь %s отключился", username));
                    myServer.unSubscribe(this);
                    myServer.broadcastMessage(String.format("%s %s", CLIENT_DISCONNECT, username), this, true);
                } catch (IOException ex) {
                    myServer.unSubscribe(this);
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    private void authentication() throws IOException {
        while (true) {
            String message = in.readUTF();
            if (message.startsWith(AUTH_CMD_PREFIX)) {
                boolean isSuccessAuth = processAuthentication(message);
                if (isSuccessAuth) {
                    break;
                }

            } else {
                out.writeUTF(AUTHERR_CMD_PREFIX + " Ошибка аутентификации");
                System.out.println("Неудачная попытка аутентификации");
            }
        }
    }

    private boolean processAuthentication(String message) throws IOException {
        String[] parts = message.split("\\s+");
        if (parts.length != 3) {
            out.writeUTF(AUTHERR_CMD_PREFIX + " Ошибка аутентификации");
        }
        String login = parts[1];
        String password = parts[2];

        AuthenticationService auth = myServer.getAuthenticationService();

        username = auth.getUsernameByLoginAndPassword(login, password);

        if (username != null) {
            if (myServer.isUsernameBusy(username)) {
                out.writeUTF(AUTHERR_CMD_PREFIX + " Логин уже используется");
                return false;
            }

            out.writeUTF(AUTHOK_CMD_PREFIX + " " + username);
            myServer.subscribe(this);
            System.out.println("Пользователь " + username + " подключился к чату");
            myServer.broadcastMessage(String.format(">>> %s присоединился к чату", username), this, true);

            return true;
        } else {
            out.writeUTF(AUTHERR_CMD_PREFIX + " Логин или пароль не соответствуют действительности");
            return false;
        }
    }

    private void readMessage() throws IOException {
        while (true) {
            String message = in.readUTF();

            System.out.println("message | " + username + ": " + message);
            if (message.startsWith(STOP_SERVER_CMD_PREFIX)) {
                System.exit(0);
            } else if (message.startsWith(END_CLIENT_CMD_PREFIX)) {
                myServer.broadcastMessage(String.format("%s %s", CLIENT_DISCONNECT, username), this, true);
                return;
            } else if (message.startsWith(CLIENT_GET_ALL)) {
                myServer.broadcastMessageToSender(String.format("%s %s", CLIENT_GET_ALL, myServer.getClients()), this, true);
                return;
            } else if (message.startsWith(PRIVATE_MSG_CMD_PREFIX)) {
                String[] parts = message.split("\\s+", 3);
                String recipient = parts[1];
                String privateMessage = parts[2];

                myServer.sendPrivateMessage(privateMessage,this, recipient);
            } else {
                myServer.broadcastMessage(message, this);
            }
        }
    }

    public void sendMessage(String sender, String message) throws IOException {

        boolean isPrefix = false;
        if(message.startsWith("/")) {
            isPrefix = true;
        }

        if (!isPrefix) {
            if (sender != null) {
                message = String.format("%s %s %s", CLIENT_MSG_CMD_PREFIX, sender, message);
            } else {
                message = String.format("%s %s", SERVER_MSG_CMD_PREFIX, message);
            }
        }

        out.writeUTF(message);
    }

    public String getUsername() {
        return username;
    }


}
