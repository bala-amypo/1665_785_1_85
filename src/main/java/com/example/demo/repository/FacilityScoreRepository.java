// package com.example.demo.repository;


// import com.example.demo.entity.FacilityScore;
// import org.springframework.data.jpa.repository.JpaRepository;

// public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {
//     FacilityScore findByPropertyId(Long propertyId);
// }

package com.example.demo.repository;

import com.example.demo.entity.FacilityScore;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface FacilityScoreRepository extends JpaRepository<FacilityScore, Long> {
    // This must return Optional to be compatible with the service logic
    Optional<FacilityScore> findByPropertyId(Long propertyId);
}
