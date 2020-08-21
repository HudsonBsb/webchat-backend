package br.com.ws.websocket.model;

import br.com.ws.websocket.entity.UserEntity;

public class User {

    private String id;
    private String name;
    private String sex = "m";
    private String image;

    public User() {
    }

    public User(UserEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.sex = entity.getSex();
        this.image = entity.getImage();
    }

    public User(String id, String name, String sex, String image) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.image = image;
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
