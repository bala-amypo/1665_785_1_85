// package com.example.demo.controller;

// import com.example.demo.entity.User;
// import com.example.demo.exception.EmailAlreadyExistsException;
// import com.example.demo.exception.InvalidCredentialsException;
// import com.example.demo.service.UserService;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/users")
// public class UserController {

//     private final UserService userService;

//     public UserController(UserService userService) {
//         this.userService = userService;
//     }

//     // REGISTER USER
//     @PostMapping("/register")
//     public User register(@RequestBody User user) {

//         // check email already exists
//         if (userService.login(user.getEmail(), user.getPassword()) != null) {
//             throw new EmailAlreadyExistsException("Email already registered");
//         }

//         return userService.register(user);
//     }

//     // LOGIN USER
//     @PostMapping("/login")
//     public String login(@RequestBody User user) {

//         User loggedUser =
//                 userService.login(user.getEmail(), user.getPassword());

//         if (loggedUser == null) {
//             throw new InvalidCredentialsException("Invalid email or password");
//         }

//         return "Login successful";
//     }
// }
package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;
import com.example.demo.security.JwtTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth Controller")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    // Constructor Injection
    public AuthController(UserService userService, JwtTokenProvider tokenProvider) {
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<User> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.register(user));
    }

    @PostMapping("/login")
    @Operation(summary = "Login and receive JWT")
    public ResponseEntity<String> login(@RequestBody User loginRequest) {
        // Implementation logic to verify password and generate token
        String token = tokenProvider.generateToken(loginRequest.getEmail());
        return ResponseEntity.ok(token);
    }
}
