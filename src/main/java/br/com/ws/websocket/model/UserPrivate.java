package br.com.ws.websocket.model;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class UserPrivate {
    private String name;

    public UserPrivate() {
    }

    public UserPrivate(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "UserPrivate{" +
                "name='" + name + '\'' +
                '}';
    }
}
