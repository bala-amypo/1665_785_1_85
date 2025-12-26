// package com.example.demo.service;
// import com.example.demo.entity.FacilityScore;
// import com.example.demo.entity.Property;
// import com.example.demo.repository.FacilityScoreRepository;
// import com.example.demo.repository.PropertyRepository;
// import org.springframework.stereotype.Service;

// @Service
// public class FacilityScoreServiceImpl implements FacilityScoreService {

//     private final FacilityScoreRepository scoreRepository;
//     private final PropertyRepository propertyRepository;

//     public FacilityScoreServiceImpl(FacilityScoreRepository scoreRepository,PropertyRepository propertyRepository) {
//         this.scoreRepository = scoreRepository;
//         this.propertyRepository = propertyRepository;
//     }

//     @Override
//     public FacilityScore addScore(Long propertyId, FacilityScore score) {
//         Property property = propertyRepository.findById(propertyId).orElseThrow();
//         score.setProperty(property);
//         return scoreRepository.save(score);
//     }

//     @Override
//     public FacilityScore getScoreByProperty(Long propertyId) {
//         return scoreRepository.findByPropertyId(propertyId);
//     }
// }

package com.example.demo.service.Impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.FacilityScoreService;
import com.example.demo.service.RatingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

    private final FacilityScoreRepository facilityScoreRepository;
    private final PropertyRepository propertyRepository;
    private final RatingService ratingService;

    public FacilityScoreServiceImpl(FacilityScoreRepository facilityScoreRepository, 
                                     PropertyRepository propertyRepository,
                                     RatingService ratingService) {
        this.facilityScoreRepository = facilityScoreRepository;
        this.propertyRepository = propertyRepository;
        this.ratingService = ratingService;
    }

    @Override
    @Transactional
    public FacilityScore addScore(Long propertyId, FacilityScore score) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // Score validation (0-10)
        if (score.getSchoolProximity() < 0 || score.getSchoolProximity() > 10) 
            throw new RuntimeException("Scores must be between 0 and 10");

        score.setProperty(property);
        FacilityScore saved = facilityScoreRepository.save(score);
        
        // Trigger rating automatically
        ratingService.generateRating(propertyId);
        return saved;
    }

    @Override
    public FacilityScore getScoreByProperty(Long propertyId) {
        return facilityScoreRepository.findByPropertyId(propertyId)
                .orElseThrow(() -> new RuntimeException("Facility score not found"));
    }
}