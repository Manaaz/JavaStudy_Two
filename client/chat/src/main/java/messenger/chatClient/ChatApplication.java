package messenger.chatClient;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Modality;
import javafx.stage.Stage;
import messenger.chatClient.controllers.AuthController;
import messenger.chatClient.controllers.ChatController;
import messenger.chatClient.controllers.SettingsController;
import messenger.chatClient.models.FileManagement;
import messenger.chatClient.models.Network;

import java.io.IOException;

public class ChatApplication extends Application {

    private Network network;
    private Stage primaryStage;
    private Stage authStage;
    private Stage settStage;
    private ChatController chatController;


    @Override
    public void start(Stage stage) throws IOException {

        this.primaryStage = stage;

        network = new Network();
        network.connect();

        openAuthDialog();
        createChatDialog();

    }

    private void openAuthDialog() throws IOException {
        FXMLLoader authLoader = new FXMLLoader(ChatApplication.class.getResource("auth-view.fxml"));
        authStage = new Stage();
        Scene scene = new Scene(authLoader.load());

        authStage.setScene(scene);
        authStage.initModality(Modality.APPLICATION_MODAL);
        authStage.initOwner(primaryStage);
        authStage.setTitle("Authentication");
        authStage.setAlwaysOnTop(true);
        authStage.show();

        AuthController chatController = authLoader.getController();

        chatController.setNetwork(network);
        chatController.setChaApplication(this);
    }

    public void showSettingsDialog() throws IOException {
        FXMLLoader settLoader = new FXMLLoader(ChatApplication.class.getResource("sett-view.fxml"));
        settStage = new Stage();
        Scene scene = new Scene(settLoader.load());

        settStage.setScene(scene);
        settStage.initModality(Modality.APPLICATION_MODAL);
        settStage.initOwner(primaryStage);
        settStage.setTitle("User settings");
        settStage.setAlwaysOnTop(true);
        settStage.show();
        SettingsController settingsController = settLoader.getController();

        settingsController.setNetwork(network);
        settingsController.setChatApplication(this);
    }

    private void createChatDialog() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatApplication.class.getResource("chat-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setAlwaysOnTop(true);

        chatController = fxmlLoader.getController();
        chatController.setNetwork(network);
    }

    public static void main(String[] args) {
        launch();
    }

    public void openChatDialog() {
        authStage.close();
        primaryStage.show();
        primaryStage.setTitle(network.getUsername());

        chatController.setUsernameTitle(network.getUsername());
        chatController.setChatApplication(this);
        chatController.openChatHistoryFile(network.getLogin());

        network.waitMessage(chatController);
        network.getAllClients();

    }

    public void closeSettingsDialog() {
        settStage.close();
    }

    public void showErrorAlert(String title, String errorMessage) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(errorMessage);
        alert.show();
    }
}