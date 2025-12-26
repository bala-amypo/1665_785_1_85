// package com.example.demo.entity;

// import jakarta.persistence.*;
// import lombok.*;
// import java.time.LocalDateTime;

// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class RatingLog {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @ManyToOne
//     private Property property;

//     private String message;
//     private LocalDateTime loggedAt;

//     @PrePersist
//     public void onCreate() {
//         loggedAt = LocalDateTime.now();
//     }
// }

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class RatingLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "property_id")
    private Property property; //

    private String message;
    private LocalDateTime loggedAt;

    @PrePersist
    protected void onCreate() {
        this.loggedAt = LocalDateTime.now(); // Auto-generated
    }
}