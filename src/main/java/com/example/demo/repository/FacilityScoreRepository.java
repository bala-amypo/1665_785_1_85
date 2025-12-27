// package com.example.demo.repository;


// import com.example.demo.entity.FacilityScore;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {
//     FacilityScore findByPropertyId(Long propertyId);
// }

package com.example.demo.repository;

import com.example.demo.entity.FacilityScore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {
    java.util.Optional<FacilityScore> findByProperty(Property property); // Required
    java.util.Optional<FacilityScore> findByPropertyId(Long propertyId);
}
