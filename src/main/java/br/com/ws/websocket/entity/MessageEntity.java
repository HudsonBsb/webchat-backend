package br.com.ws.websocket.entity;

import br.com.ws.websocket.model.Message;
import br.com.ws.websocket.model.User;
import br.com.ws.websocket.model.UserPrivate;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
public class MessageEntity {

    private User user;
    private String message;
    private LocalDateTime time;
    private Boolean isPrivate = false;
    private UserPrivate userPrivate;

    public MessageEntity() {
    }

    public MessageEntity(Message message) {
        this.user = message.getUser();
        this.message = message.getMessage();
        this.isPrivate = message.getPrivate();
        this.userPrivate = message.getUserPrivate();
    }

    public Message toModel() {
        var msg = new Message();
        msg.setUser(this.user);
        msg.setMessage(this.message);
        msg.setTime(this.time);
        msg.setPrivate(this.isPrivate);
        msg.setUserPrivate(this.userPrivate);
        return msg;
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
        return "MessageEntity{" +
                "user=" + user +
                ", message='" + message + '\'' +
                ", time=" + time +
                ", isPrivate=" + isPrivate +
                ", userPrivate=" + userPrivate +
                '}';
    }
}
