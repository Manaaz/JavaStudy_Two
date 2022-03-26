package messenger.chatClient.controllers;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import messenger.chatClient.models.Network;

public class ChatController {

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
    }

    private Network network;

    public void setNetwork(Network network) {
        this.network = network;
    }

    private void sendMessage() {

        String message = inputField.getText().trim();
        inputField.clear();

        if (message.trim().isEmpty()) {
            return;
        }

        //String formattedMessage = getMessageData(message);

        network.sendMessage(message);

        //appendMessage("Я: " + message);

    }
/*
    public String getMessageData(String message) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd hh:mm:ss");
        return "" + simpleDateFormat.format(new Date()) + System.lineSeparator()
                + message + System.lineSeparator() + System.lineSeparator();
    }
*/
    public void appendMessage(String message) {
        chatMessages.insertText(0, message + System.lineSeparator());
    }

}