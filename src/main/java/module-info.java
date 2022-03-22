module messenger.chatclient {
    requires javafx.controls;
    requires javafx.fxml;
    requires json;

    opens messenger.chatclient to javafx.fxml;
    exports messenger.chatclient;
    exports messenger.chatclient.controllers;
    opens messenger.chatclient.controllers to javafx.fxml;
}