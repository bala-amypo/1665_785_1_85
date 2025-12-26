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
    // Used to list all logs for a specific property
    List<RatingLog> findByPropertyId(Long propertyId);
}

