// package com.example.demo.repository;


// import com.example.demo.entity.FacilityScore;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {
//     FacilityScore findByPropertyId(Long propertyId);
// }

package com.example.demo.repository;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property; // Mandatory Import
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {
    // Both are required by different integration tests
    Optional<FacilityScore> findByProperty(Property property);
    Optional<FacilityScore> findByPropertyId(Long propertyId);
}