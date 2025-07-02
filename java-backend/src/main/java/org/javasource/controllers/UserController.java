package org.javasource.controllers;

import org.javasource.models.dto.UserLoginDTO;
import org.javasource.models.dto.UserRegisterDTO;
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

    @GetMapping("/allUsers")
    public List<User> list() {
        return service.listAll();
    }

    @PostMapping("/login")
    public User LoginController(@RequestBody UserLoginDTO request) {
        return service.loginService(request);
    }

    @PostMapping("/register")
    public User RegisterController(@RequestBody UserRegisterDTO request) {
        return service.registerService(request);
    }
}
