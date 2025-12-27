// package com.example.demo.repository;
// import com.example.demo.entity.RatingLog;
// import org.springframework.data.jpa.repository.JpaRepository;
// import java.util.List;

// public interface RatingLogRepository extends JpaRepository<RatingLog, Long> {
//     List<RatingLog> findByPropertyId(Long propertyId);
// }



package com.example.demo.repository;

import com.example.demo.entity.RatingLog;
import com.example.demo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RatingLogRepository extends JpaRepository<RatingLog, Long> {
    // Required to fix "cannot find symbol findByProperty"
    List<RatingLog> findByProperty(Property property);
    
    // Required for Step 4.5
    List<RatingLog> findByPropertyId(Long propertyId);
}