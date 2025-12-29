package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;

import java.util.*;

@Entity
@Table(name = "properties")
public class Property {

    // ---------- primary fields ----------

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;

    private String title;
    private String address;
    private String city;

    @Min(1)
    private Double price;

    @Min(value = 100 , message = "Area must be atleast 100 sqFt")
    private Double areaSqFt;

    // ---------- relationships (OUTPUT only) ----------

    @OneToOne(mappedBy = "property", cascade = CascadeType.ALL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private RatingResult ratingResult;

    @OneToMany(mappedBy = "property", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private List<RatingLog> ratingLogs = new ArrayList<>();

    @ManyToMany(mappedBy = "assignedProperties")
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Set<User> assignedUsers = new HashSet<>();

    // ---------- constructors ----------

    public Property() {}

    public Property(String title, String address, String city,
                    Double price, Double areaSqFt) {
        this.title = title;
        this.address = address;
        this.city = city;
        this.price = price;
        this.areaSqFt = areaSqFt;
    }

    // ---------- getters & setters ----------

    public Long getId() {
        return id;
    }

    public RatingResult getRatingResult() {
        return ratingResult;
    }

    public void setRatingResult(RatingResult ratingResult) {
        this.ratingResult = ratingResult;
    }

    public List<RatingLog> getRatingLogs() {
        return ratingLogs;
    }

    public Set<User> getAssignedUsers() {
        return assignedUsers;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getAreaSqFt() {
        return areaSqFt;
    }

    public void setAreaSqFt(Double areaSqFt) {
        this.areaSqFt = areaSqFt;
    }

    // ---------- helper ----------

    public void addRatingLog(RatingLog log) {
        this.ratingLogs.add(log);
        log.setProperty(this);
    }
}