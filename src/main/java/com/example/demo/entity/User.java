// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;

// @Entity
// @Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String name;

//     @Column(nullable = false, unique = true)
//     private String email;

//     private String password;

//     private String role = "ANALYST";
// }
package com.example.demo.entity;

import jakarta.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;
    private String password;

    // Getters and Setters
}
