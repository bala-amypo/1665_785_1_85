

package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }
    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.register(user);
    }


    @PostMapping("/login")
    public String login(@RequestBody User user) {
        User loggedUser = userService.login(user.getEmail(), user.getPassword());

        if (loggedUser != null) {
            return "Login successful";
        }
        return "Invalid email or password";
    }
}
