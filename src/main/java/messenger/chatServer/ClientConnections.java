package messenger.chatServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

public class ClientConnections {

    private static DataInputStream in;
    private static DataOutputStream out;
    private static String userName;

    public void clientConnect(ServerSocket serverSocket, Socket clientSocket) throws IOException {

        in = new DataInputStream(clientSocket.getInputStream());
        out = new DataOutputStream(clientSocket.getOutputStream());

        try {

            while (true) {

                String message = in.readUTF();

                if (message.equals("/server-stop")) {
                    System.out.println("Сервер остановлен.");
                    System.exit(0);
                }
                System.out.println("Клиент: " + message);
                out.writeUTF(message); //.toUpperCase());
                out.writeUTF("Сообщение с сервера! Тестовое."
                                    + userName
                                    + System.lineSeparator());
            }

        } catch (SocketException e) {
            clientSocket.close();
            System.out.println("Клиент отключился.");
        }
    }
}
