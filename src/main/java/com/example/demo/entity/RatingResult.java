// package com.example.demo.entity;
// import jakarta.persistence.*;
// import lombok.*;
// import java.time.LocalDateTime;

// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class RatingResult {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     @OneToOne
//     private Property property;

//     private Double finalRating;
//     private String ratingCategory;
//     private LocalDateTime ratedAt;

//     @PrePersist
//     public void onCreate() {
//         ratedAt = LocalDateTime.now();
//     }
// }
package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data
public class RatingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "property_id", nullable = false)
    private Property property;

    private Double finalRating;

    private String ratingCategory;

    private LocalDateTime ratedAt;

    @PrePersist
    protected void onCreate() {
        this.ratedAt = LocalDateTime.now();
    }
}
