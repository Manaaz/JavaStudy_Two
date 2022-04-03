package messenger.chatClient.controllers;

import messenger.chatClient.ChatApplication;
import messenger.chatClient.models.Network;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class AuthController {
    @FXML
    public TextField loginField;
    @FXML
    public PasswordField passwordField;
    private Network network;
    private ChatApplication chatApplication;

    @FXML
    public void checkAuth() {
        String login = loginField.getText().trim();
        String password = passwordField.getText().trim();

        if (login.length() == 0 || password.length() == 0) {
            chatApplication.showErrorAlert("Ошибка ввода при аутентификации", "Поля не должны быть пустыми");

            return;
        }

        String authErrorMessage = network.sendAuthMessage(login, password);

        if (authErrorMessage == null) {
            chatApplication.openChatDialog();
        } else {
            chatApplication.showErrorAlert("Ошибка аутентификации", authErrorMessage);
        }
    }

    public void setChaApplication(ChatApplication chatApplication) {
        this.chatApplication = chatApplication;
    }

    public void setNetwork(Network network) {
        this.network = network;
    }


}
