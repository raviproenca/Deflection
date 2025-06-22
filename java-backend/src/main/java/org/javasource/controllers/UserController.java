package org.javasource.controllers;

import org.javasource.models.entity.User;
import org.javasource.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping
    public List<User> lista() {
        return service.listAll();
    }

    @PostMapping
    public User save(@RequestBody User user) {
        return service.save(user);
    }
}
