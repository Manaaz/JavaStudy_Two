package com.example.lesson4_jfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChatMessage {

    StringProperty message = new SimpleStringProperty();
    StringProperty date = new SimpleStringProperty();

    public ChatMessage(String message, String date) {
        this.message = new SimpleStringProperty(message);
        this.date = new SimpleStringProperty(date);
    }

    public StringProperty MessageColumnProperty() {
        return message;
    }

    public StringProperty DateColumnProperty() {
        return date;
    }



    public String getMessage() {
        return message.get();
    }
    public StringProperty messageProperty() {
        return message;
    }
    public void setMessage(String message) {
        this.message.set(message);
    }

    public String getDate() {
        return date.get();
    }
    public StringProperty dateProperty() {
        return date;
    }
    public void setDate(String date) {
        this.date.set(date);
    }
}
