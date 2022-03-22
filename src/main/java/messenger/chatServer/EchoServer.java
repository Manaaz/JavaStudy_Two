package messenger.chatServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final int SERVER_PORT = 8186;

    public static void main(String[] args) {

        try(ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {

            while(true) {

                System.out.println("Ожидание подключения...");
                Socket clientSocket = serverSocket.accept();
                System.out.println("Соединение установлено.");

                Thread thread = new Thread(() -> {
                    ClientConnections clientConnections = new ClientConnections();
                    try {
                        clientConnections.clientConnect(serverSocket, clientSocket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

                thread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
