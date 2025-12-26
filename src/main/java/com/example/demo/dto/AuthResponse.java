package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor // This creates the (String, String, String) constructor needed by AuthController
public class AuthResponse {
    private String token;
    private String email;
    private String role;

    // Manual constructor in case Lombok @AllArgsConstructor is acting up
    public AuthResponse(String token, String email, String role) {
        this.token = token;
        this.email = email;
        this.role = role;
    }
}