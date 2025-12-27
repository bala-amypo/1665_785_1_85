// package com.example.demo.repository;

// import com.example.demo.entity.RatingResult;

// import org.springframework.data.jpa.repository.JpaRepository;

// public interface RatingResultRepository extends JpaRepository<RatingResult, Long> {
//     RatingResult findByPropertyId(Long propertyId);
// }

package com.example.demo.repository;

import com.example.demo.entity.RatingResult;
import com.example.demo.entity.Property; // Mandatory Import
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface RatingResultRepository extends JpaRepository<RatingResult, Long> {
    Optional<RatingResult> findByProperty(Property property);
    Optional<RatingResult> findByPropertyId(Long propertyId);
}