package messenger.chatclient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import messenger.chatclient.controllers.ChatController;
import messenger.chatclient.models.Network;

import java.io.IOException;

public class ChatApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatApplication.class.getResource("chat-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        stage.setTitle("Messenger");
        stage.setScene(scene);
        stage.show();

        Network network = new Network();
        ChatController chatController = fxmlLoader.getController();

        chatController.setNetwork(network);

        network.connect();
        network.waitMessage(chatController);
    }

    public static void main(String[] args) {
        launch();
    }
}