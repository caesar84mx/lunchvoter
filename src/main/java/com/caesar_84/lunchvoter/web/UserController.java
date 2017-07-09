package com.caesar_84.lunchvoter.web;

import com.caesar_84.lunchvoter.LoggedUser;
import com.caesar_84.lunchvoter.domain.User;
import com.caesar_84.lunchvoter.service.implementations.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/rest-api/v1")
public class UserController {
    @Autowired
    private UserService service;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> get(@PathVariable("id") int id) {
        logger.info("User " + LoggedUser.id() + " is getting details for user " + id);
        return service.get(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<List<User>> getAll() {
        logger.info("User " + LoggedUser.id() + " is getting users list");
        return service.getAll();
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<User> create(@RequestBody User user) {
        logger.info("Creating an user " + user);
        return service.create(user);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    ResponseEntity<String> changeNameAndPassword(@RequestParam("name") String name,
                                                 @RequestParam("password") String password) {
        logger.info("Changing user name and/or password");
        return service.changeNameAndPassword(name, password);
    }

    @RequestMapping(value = "/users/admin/{id}",method = RequestMethod.DELETE)
    ResponseEntity delete(@PathVariable("id") int id) {
        logger.info("Deleting user " + id);
        return service.delete(id);
    }

    @RequestMapping(value = "/users", method = RequestMethod.DELETE)
    ResponseEntity deleteOneself() {
        logger.info("User " + LoggedUser.id() + " is deleting himself");
        return service.deleteOneself();
    }

    @RequestMapping(value = "/users/admin/disable/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> disable(@PathVariable("id") int id) {
        logger.info("Disabling user " + id);
        return service.disable(id);
    }

    @RequestMapping(value = "/users/admin/enable/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    ResponseEntity<String> enable(@PathVariable("id") int id) {
        logger.info("Enabling user " + id);
        return service.enable(id);
    }
}