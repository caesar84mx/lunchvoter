package com.caesar_84.lunchvoter.service.implementations;

import com.caesar_84.lunchvoter.LoggedUser;
import com.caesar_84.lunchvoter.domain.Role;
import com.caesar_84.lunchvoter.domain.User;
import com.caesar_84.lunchvoter.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository repository;

    public ResponseEntity<User> create(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return new ResponseEntity<>(repository.save(user), HttpStatus.CREATED);
    }

    public ResponseEntity<String> changeNameAndPassword(String name, String password) {
        password = encoder.encode(password);

        int rowsAffected = repository.update(LoggedUser.id(), name, password);
        String responseBody = rowsAffected > 0 ? "updated" : "failed to update";

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity delete(int id) {
        User user = repository.getOne(id);
        if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
        repository.delete(id);

        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<String> disable(int id) {
        User user = repository.getOne(id);
        if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            return new ResponseEntity<>("Can not disable an admin", HttpStatus.FORBIDDEN);
        }

        int rowsAffected = repository.disable(id);
        String responseBody = rowsAffected > 0 ? "user " + id + " disabled" : "no users disabled";

        return new ResponseEntity<>( responseBody, HttpStatus.OK);
    }

    public ResponseEntity<String> enable(@PathVariable("id") int id) {
        User user = repository.getOne(id);
        if (user.getRoles().contains(Role.ROLE_ADMIN)) {
            return new ResponseEntity<>("Can not enable an admin", HttpStatus.FORBIDDEN);
        }

        int rowsAffected = repository.enable(id);
        String responseBody = rowsAffected > 0 ? "user " + id + " enabled" : "no users enabled";

        return new ResponseEntity<>(responseBody, HttpStatus.OK);
    }

    public ResponseEntity deleteOneself() {
        repository.delete(LoggedUser.id());

        return new ResponseEntity(HttpStatus.OK);
    }

    public ResponseEntity<User> get(int id) {
        return new ResponseEntity<>(repository.findOne(id), HttpStatus.OK);
    }

    public ResponseEntity<List<User>> getAll() {
        return new ResponseEntity<>(repository.findAll(new Sort("name", "email")), HttpStatus.OK);
    }
}