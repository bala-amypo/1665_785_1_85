// package com.example.demo.entity;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.Min;
// import lombok.*;

// import java.util.List;

// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class Property {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String title;
//     private String address;
//     private String city;

//     @Min(1)
//     private Double price;

//     @Min(100)
//     private Double areaSqFt;

//     @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
//     private List<RatingLog> ratingLogs;
// }
package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Min;
import lombok.Data;
import java.util.List;

@Entity
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String address;
    private String city;
    
    @DecimalMin(value = "0.01", message = "Price > 0")
    private Double price;
    
    @Min(value = 100, message = "Area >= 100")
    private Double areaSqFt;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RatingLog> ratingLogs;
}