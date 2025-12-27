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

    /**
     * Finds audit logs by the Property entity. 
     * Required for integration tests to verify property-specific history.
     */
    List<RatingLog> findByProperty(Property property);

    /**
     * Finds audit logs by Property ID. 
     * Used by the service layer to retrieve logs.
     */
    List<RatingLog> findByPropertyId(Long propertyId);
}