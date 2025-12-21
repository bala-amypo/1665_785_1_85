package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

@Entity
public class FacilityScore {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Property property;

    private Integer schoolProximity;
    private Integer hospitalProximity;
    private Integer transportAccess;
    private Integer safetyScore;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Property getProperty() { return property; }
    public void setProperty(Property property) {
        this.property = property;
    }

    public Integer getSchoolProximity() { return schoolProximity; }
    public void setSchoolProximity(Integer schoolProximity) {
        this.schoolProximity = schoolProximity;
    }

    public Integer getHospitalProximity() { return hospitalProximity; }
    public void setHospitalProximity(Integer hospitalProximity) {
        this.hospitalProximity = hospitalProximity;
    }

    public Integer getTransportAccess() { return transportAccess; }
    public void setTransportAccess(Integer transportAccess) {
        this.transportAccess = transportAccess;
    }

    public Integer getSafetyScore() { return safetyScore; }
    public void setSafetyScore(Integer safetyScore) {
        this.safetyScore = safetyScore;
    }
}
