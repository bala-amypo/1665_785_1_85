// package com.example.demo.repository;

// import com.example.demo.entity.RatingResult;

// import org.springframework.data.jpa.repository.JpaRepository;

// public interface RatingResultRepository extends JpaRepository<RatingResult, Long> {
//     RatingResult findByPropertyId(Long propertyId);
// }


package com.example.demo.repository;

import com.example.demo.entity.RatingResult;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RatingResultRepository extends JpaRepository<RatingResult, Long> {
    // Must return Optional for .orElseThrow() to work in the service
    Optional<RatingResult> findByPropertyId(Long propertyId);
}