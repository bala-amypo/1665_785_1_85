package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToOne;

@Entity
public class RatingResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    private Property property;

    private Double finalRating;
    private String ratingCategory;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Property getProperty() { return property; }
    public void setProperty(Property property) {
        this.property = property;
    }

    public Double getFinalRating() { return finalRating; }
    public void setFinalRating(Double finalRating) {
        this.finalRating = finalRating;
    }

    public String getRatingCategory() { return ratingCategory; }
    public void setRatingCategory(String ratingCategory) {
        this.ratingCategory = ratingCategory;
    }
}
