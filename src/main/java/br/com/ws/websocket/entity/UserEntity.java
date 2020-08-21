package br.com.ws.websocket.entity;

import br.com.ws.websocket.model.User;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;

@Document("user")
public class UserEntity {

    @Id
    @MongoId
    private String id;
    private String name;
    private String sex = "m";
    private String image;

    public UserEntity() {
    }

    public UserEntity(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.sex = user.getSex();
        this.image = user.getImage();
    }

    public User toModel() {
        var user = new User();
        user.setId(this.id);
        user.setName(this.name);
        user.setSex(this.sex);
        user.setImage(this.image);
        return user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
