package com.example.lesson4_jfx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ChatController.class.getResource("chat-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Сетевой чат.");
        stage.setScene(scene);
        //stage.setY(1500);
        //stage.setX(1000);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}