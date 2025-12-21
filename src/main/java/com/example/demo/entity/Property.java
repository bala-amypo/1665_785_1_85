package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.List;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String address;
    private String city;
    private Double price;
    private Double areaSqFt;

    @OneToMany(mappedBy = "property", cascade = CascadeType.REMOVE)
    private List<RatingLog> ratingLogs;
}