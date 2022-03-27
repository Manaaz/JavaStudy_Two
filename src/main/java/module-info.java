module messenger.chatclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;

    opens messenger.chatClient to javafx.fxml;
    exports messenger.chatClient;
    exports messenger.chatClient.controllers;
    opens messenger.chatClient.controllers to javafx.fxml;
}