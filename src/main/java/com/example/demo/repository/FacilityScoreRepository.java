// package com.example.demo.repository;


// import com.example.demo.entity.FacilityScore;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {
//     FacilityScore findByPropertyId(Long propertyId);
// }

package com.example.demo.repository;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {
    // Required to fix "cannot find symbol findByProperty" in Integration Tests
    Optional<FacilityScore> findByProperty(Property property);
    
    // Used by the Service layer to fetch scores by ID
    Optional<FacilityScore> findByPropertyId(Long propertyId);
}