package messenger.chatclient.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import messenger.chatclient.models.Network;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ChatController {

    @FXML
    private TextField userNameField;

    @FXML
    private TextField inputField;

    @FXML
    private TextArea chatMessages;

    @FXML
    private ListView<String> usersList;

    @FXML
    private Button sendButton;

    @FXML
    public void initialize() {
        usersList.setItems(FXCollections.observableArrayList("Тимофей", "Дмитрий", "Диана", "Арман"));
        sendButton.setOnAction(event -> sendMessage());
        inputField.setOnAction(event -> sendMessage());

        userNameField.setOnAction(event -> sendUserName());
    }

    private Network network;

    public void setNetwork(Network network) {
        this.network = network;
    }

    private void sendMessage() {

        String stringUserName = userNameField.getText().trim();
        if (stringUserName.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Не указано имя пользователя!");
            return;
        }

        String message = inputField.getText().trim();
        inputField.clear();

        if (message.trim().isEmpty()) {
            return;
        }

        network.sendMessage(userNameField.getText(), getMessageData(message));

    }

    public String getMessageData(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        return simpleDateFormat.format(new Date()) + System.lineSeparator()
                + message + System.lineSeparator() + System.lineSeparator();
    }

    public void appendMessage(String message) {
        chatMessages.insertText(0, message);
    }

    private void sendUserName() {

        String stringUserName = userNameField.getText().trim();
        if (stringUserName.trim().isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setContentText("Не указано имя пользователя!");
            return;
        }

        ObservableList<String> users = usersList.getItems();

        if (!users.contains(stringUserName)){
            users.add(stringUserName);
            usersList.setItems(users);
            userNameField.setEditable(false);
        }
    }



}