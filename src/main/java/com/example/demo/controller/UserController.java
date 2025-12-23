package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.InvalidCredentialsException;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // REGISTER USER
    @PostMapping("/register")
    public User register(@RequestBody User user) {

        // check email already exists
        if (userService.login(user.getEmail(), user.getPassword()) != null) {
            throw new EmailAlreadyExistsException("Email already registered");
        }

        return userService.register(user);
    }

    // LOGIN USER
    @PostMapping("/login")
    public String login(@RequestBody User user) {

        User loggedUser =
                userService.login(user.getEmail(), user.getPassword());

        if (loggedUser == null) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return "Login successful";
    }
}
