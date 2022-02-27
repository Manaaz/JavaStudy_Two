package com.example.lesson4_jfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.text.SimpleDateFormat;
import java.util.Date;


public class ChatController {

    @FXML
    private TableView<ChatMessage> chatMessages;

    @FXML
    private TableColumn<ChatMessage, String> messageColumn;

    @FXML
    private TableColumn<ChatMessage, String> dateColumn;

    @FXML
    private TextField newMessageInput;


    @FXML
    private TableView<ChatUsers> usersList;

    @FXML
    private TableColumn<ChatUsers, String> userColumn;


    @FXML
    void initialize() {

        userColumn.setCellValueFactory(new PropertyValueFactory<ChatUsers, String>("user"));

        usersList.setItems(FXCollections.observableArrayList(
                new ChatUsers("Василий"),
                new ChatUsers("Василий 2")
        ));

        messageColumn.setCellValueFactory(new PropertyValueFactory<ChatMessage, String>("message"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<ChatMessage, String>("date"));

        chatMessages.setItems(FXCollections.observableArrayList(
                new ChatMessage("a", new Date().toString()),
                new ChatMessage("b", new Date().toString()),
                new ChatMessage("c", new Date().toString()),
                new ChatMessage("d", new Date().toString())
        ));

    }

    @FXML
    void btnSendMessage() {
        String message = newMessageInput.getText().trim();
        if (message.length() != 0) {
            addMessageToTable(message);
            newMessageInput.clear();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Input Error");
            alert.setHeaderText("Строка не должна быть пустой");
            alert.show();
        }
    }

    private void addMessageToTable(String message) {
        ObservableList<ChatMessage> rowArray = chatMessages.getItems();
        rowArray.add(
                0,
                new ChatMessage(
                        message,
                        new Date().toString()
                        )
                    );
    }

    @FXML
    void btnOnClickHelp() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("HELP");
        alert.setHeaderText("Разработчик: Шумков Сергей. \n Учебный проект GeekBrains.");
        alert.show();
    }


}