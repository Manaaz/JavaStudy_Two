package messenger.chatClient.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import messenger.chatClient.ChatApplication;
import messenger.chatClient.models.Network;

public class SettingsController {
    @FXML
    private Button sendButton;

    @FXML
    private TextField newUserName;

    private Network network;
    private ChatApplication chatApplication;

    @FXML
    public void initialize() {
        sendButton.setOnAction(event -> sendMessage());
        newUserName.setOnAction(event -> sendMessage());
    }

    private void sendMessage() {
        String name = newUserName.getText().trim();
        if (name.length() == 0 ) {
            chatApplication.showErrorAlert("Ошибка ввода имени пользователя", "Имя не должны быть пустым");
            return;
        }

        network.sendRenameMessage(name);
        chatApplication.closeSettingsDialog();

    }

    public void setChatApplication(ChatApplication chaApplication) {
        this.chatApplication = chaApplication;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }

}
