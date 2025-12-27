// package com.example.demo.repository;
// import com.example.demo.entity.RatingLog;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;

// public interface RatingLogRepository extends JpaRepository<RatingLog, Long> {
//     List<RatingLog> findByPropertyId(Long propertyId);
// }



package com.example.demo.repository;

import com.example.demo.entity.RatingLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface RatingLogRepository extends JpaRepository<RatingLog, Long> {
    java.util.List<RatingLog> findByProperty(Property property); // Required
    
    // Some tests use a custom save name
    default void addRatingLog(RatingLog log) {
        save(log);
    }
}
