// package com.example.demo.service;
// import com.example.demo.entity.FacilityScore;
// import com.example.demo.entity.Property;
// import com.example.demo.entity.RatingResult;
// import com.example.demo.repository.FacilityScoreRepository;
// import com.example.demo.repository.PropertyRepository;
// import com.example.demo.repository.RatingResultRepository;
// import org.springframework.stereotype.Service;

// @Service
// public class RatingServiceImpl implements RatingService {

//     private final FacilityScoreRepository scoreRepository;
//     private final RatingResultRepository resultRepository;
//     private final PropertyRepository propertyRepository;
//     private final RatingLogService ratingLogService;

//     public RatingServiceImpl(FacilityScoreRepository scoreRepository,RatingResultRepository resultRepository, PropertyRepository propertyRepository,RatingLogService ratingLogService) {
//     this.scoreRepository = scoreRepository;
//         this.resultRepository = resultRepository;
//         this.propertyRepository = propertyRepository;
//         this.ratingLogService = ratingLogService;
//     }

//     @Override
//     public RatingResult generateRating(Long propertyId) {
//         FacilityScore score = scoreRepository.findByPropertyId(propertyId);

//         double avg = (score.getSchoolProximity() + score.getHospitalProximity()+ score.getTransportAccess()+ score.getSafetyScore()) / 4.0;

//         String category =avg < 4 ? "POOR" :
//                 avg < 6 ? "AVERAGE" :
//                 avg < 8 ? "GOOD" : "EXCELLENT";

//         Property property = propertyRepository.findById(propertyId).orElseThrow();

//         RatingResult result = new RatingResult();
//         result.setProperty(property);
//         result.setFinalRating(avg);
//         result.setRatingCategory(category);

//         ratingLogService.addLog(propertyId, "Rating generated: " + category);

//         return resultRepository.save(result);
//     }

//     @Override
//     public RatingResult getRating(Long propertyId) {
//         return resultRepository.findByPropertyId(propertyId);
//     }
// }

package com.example.demo.service.Impl;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.entity.RatingLog;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingResultRepository;
import com.example.demo.repository.RatingLogRepository;
import com.example.demo.service.RatingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingResultRepository ratingResultRepository;
    private final FacilityScoreRepository facilityScoreRepository;
    private final PropertyRepository propertyRepository;
    private final RatingLogRepository ratingLogRepository;

    // Requirement: Constructor Injection
    public RatingServiceImpl(
            RatingResultRepository ratingResultRepository,
            FacilityScoreRepository facilityScoreRepository,
            PropertyRepository propertyRepository,
            RatingLogRepository ratingLogRepository) {
        this.ratingResultRepository = ratingResultRepository;
        this.facilityScoreRepository = facilityScoreRepository;
        this.propertyRepository = propertyRepository;
        this.ratingLogRepository = ratingLogRepository;
    }

    @Override
    @Transactional
    public RatingResult generateRating(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // FIX: Handling the Optional to resolve the compilation error
        FacilityScore score = facilityScoreRepository.findByPropertyId(propertyId)
                .orElseThrow(() -> new RuntimeException("Facility score not found for property: " + propertyId));

        // Step 1: Weighted Calculation (30% School, 20% Hospital, 20% Transport, 30% Safety)
        double finalRating = (score.getSchoolProximity() * 0.3) +
                             (score.getHospitalProximity() * 0.2) +
                             (score.getTransportAccess() * 0.2) +
                             (score.getSafetyScore() * 0.3);

        // Step 2: Determine Category based on thresholds
        String category;
        if (finalRating >= 8) category = "EXCELLENT";
        else if (finalRating >= 6) category = "GOOD";
        else if (finalRating >= 4) category = "AVERAGE";
        else category = "POOR";

        // Step 3: Save Rating Result
        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(finalRating);
        result.setRatingCategory(category);
        
        RatingResult savedResult = ratingResultRepository.save(result);

        // Step 4: Write to Rating Log
        RatingLog log = new RatingLog();
        log.setProperty(property);
        log.setMessage("System generated rating: " + category + " with score: " + finalRating);
        ratingLogRepository.save(log);

        return savedResult;
    }

    @Override
    public List<RatingResult> getAllRatings() {
        return ratingResultRepository.findAll();
    }

    @Override
    public RatingResult getRatingByProperty(Long propertyId) {
        return ratingResultRepository.findByPropertyId(propertyId)
                .orElseThrow(() -> new RuntimeException("Rating result not found"));
    }
}
