package com.example.demo.repository;

import com.example.demo.entity.RatingResult;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RatingResultRepository extends JpaRepository<RatingResult, Long> {
    RatingResult findByPropertyId(Long propertyId);
}
