package br.com.ws.websocket.service;

import br.com.ws.websocket.entity.UserEntity;
import br.com.ws.websocket.model.User;
import br.com.ws.websocket.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> getAll() {
        return this.repository.findAll()
                .stream()
                .map(UserEntity::toModel)
                .collect(Collectors.toList());
    }

    public void save(User user) {
        this.repository.save(new UserEntity(user));
    }

    public void deleteById(String id) {
        this.repository.deleteById(id);
    }

    public Optional<User> getUserById(String id) {
        return this.repository.findById(id)
                .map(UserEntity::toModel);
    }

    public Optional<User> getUserByName(String name) {
        return this.repository.findByName(name)
                .map(UserEntity::toModel);
    }

    public void deleteAll() {
        this.repository.deleteAll();
    }

    public void delete(User user) {
        var entity = new UserEntity(user);
        this.repository.delete(entity);
    }
}
