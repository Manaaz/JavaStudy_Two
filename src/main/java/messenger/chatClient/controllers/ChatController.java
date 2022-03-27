package messenger.chatClient.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import messenger.chatClient.models.Network;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Date;

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
    private Label usernameTitle;

    private String selectedRecipient;

    @FXML
    public void initialize() {
        //usersList.setItems(FXCollections.observableArrayList("Тимофей", "Дмитрий", "Диана", "Арман"));
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

        if (selectedRecipient != null) {
            network.sendPrivateMessage(selectedRecipient, message);
        } else {
            network.sendMessage(message);
        }

        appendMessage("Я: " + message);

    }

    public void appendMessage(String message) {
        String timeStamp = DateFormat.getInstance().format(new Date());
        String lineSeparator = System.lineSeparator();
        String newChatMessage = timeStamp
                                + lineSeparator
                                + message
                                + lineSeparator + lineSeparator;

        chatMessages.insertText(0, newChatMessage);
    }

    public void appendServerMessage(String serverMessage) {
        String lineSeparator = System.lineSeparator();
        String newChatMessage = serverMessage
                                + lineSeparator + lineSeparator;
    }

    public void setUsernameTitle(String username) {
        this.usernameTitle.setText(username);
    }

    public void userListClearAndUpdateFromStringArray(String stringClients) {
        String[] clients = stringClients.split(System.lineSeparator());
        for (String client: clients) {
            if (!client.trim().isEmpty()) {
                userListAddClient(client);
            }
        }
    }

    public void userListAddClient(String client) {
        ObservableList<String> items = usersList.getItems();
        int clientIndex = items.indexOf(client);
        if (clientIndex == -1) {
            items.add(client);
        }
        usersList.setItems(items);
        usersList.refresh();
    }
    public void userListRemoveClient(String client) {
        ObservableList<String> items = usersList.getItems();
        int clientIndex = items.indexOf(client);
        if (clientIndex != -1) {
            items.remove(clientIndex);
        }
        usersList.setItems(items);
    }

}