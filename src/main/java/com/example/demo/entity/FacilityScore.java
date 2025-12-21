package com.example.demo.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class FacilityScore {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "property_id")
    private Property property;

    @Min(0) @Max(10)
    private Integer schoolProximity;
    @Min(0) @Max(10)
    private Integer hospitalProximity;
    @Min(0) @Max(10)
    private Integer transportAccess;
    @Min(0) @Max(10)
    private Integer safetyScore;
}