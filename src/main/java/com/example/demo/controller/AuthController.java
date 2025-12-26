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

import com.example.demo.dto.AuthResponse;
import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.security.JwtTokenProvider;
import com.example.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Auth Controller", description = "Endpoints for user registration and authentication")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JwtTokenProvider tokenProvider;

    // Requirement: Constructor Injection
    public AuthController(AuthenticationManager authenticationManager, 
                          UserService userService, 
                          JwtTokenProvider tokenProvider) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.tokenProvider = tokenProvider;
    }

    @PostMapping("/register")
    @Operation(summary = "Register a new user")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        User user = new User();
        user.setName(registerRequest.getName());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        user.setRole(registerRequest.getRole());

        User registeredUser = userService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    @Operation(summary = "Authenticate user and return JWT token")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        // Step 1: Perform standard authentication
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Step 2: Fix for "Optional cannot be converted to User"
        // We unwrap the Optional here to get the raw User entity
        User user = userService.findByEmail(loginRequest.getEmail())
                .orElseThrow(() -> new RuntimeException("User not found with email: " + loginRequest.getEmail()));

        // Step 3: Generate Token using the (Long, String, String) signature
        String jwt = tokenProvider.generateToken(user.getId(), user.getEmail(), user.getRole());

        // Step 4: Return consistent response structure for tests
        return ResponseEntity.ok(new AuthResponse(jwt, user.getEmail(), user.getRole()));
    }
}