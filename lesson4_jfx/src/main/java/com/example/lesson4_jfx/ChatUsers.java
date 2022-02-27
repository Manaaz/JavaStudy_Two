package com.example.lesson4_jfx;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ChatUsers {

    StringProperty user = new SimpleStringProperty();

    public ChatUsers(String user) {
        this.user = new SimpleStringProperty(user);
    }

    public String getUser() {
        return user.get();
    }
    public StringProperty userProperty() {
        return user;
    }
    public void setUser(String user) {
        this.user.set(user);
    }

}
