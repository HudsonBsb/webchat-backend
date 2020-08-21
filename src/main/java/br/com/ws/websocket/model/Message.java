package br.com.ws.websocket.model;

import java.time.LocalDateTime;

public class Message {

    private User user;
    private String message;
    private LocalDateTime time;
    private Boolean isPrivate = false;
    private UserPrivate userPrivate;

    public Message() {
    }

    public Message(User user, String message, LocalDateTime time, Boolean isPrivate, UserPrivate userPrivate) {
        this.user = user;
        this.message = message;
        this.time = time;
        this.isPrivate = isPrivate;
        this.userPrivate = userPrivate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public Boolean getPrivate() {
        return isPrivate;
    }

    public void setPrivate(Boolean aPrivate) {
        isPrivate = aPrivate;
    }

    public UserPrivate getUserPrivate() {
        return userPrivate;
    }

    public void setUserPrivate(UserPrivate userPrivate) {
        this.userPrivate = userPrivate;
    }

    @Override
    public String toString() {
        return "Message{" +
                "user=" + user +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", isPrivate=" + isPrivate +
                ", userPrivate=" + userPrivate +
                '}';
    }
}
