package com.example.demo.repository;


import com.example.demo.entity.FacilityScore;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {
    FacilityScore findByPropertyId(Long propertyId);
}
