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
import com.example.demo.entity.RatingResult;
import com.example.demo.entity.RatingLog;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingResultRepository;
import com.example.demo.repository.RatingLogRepository;
import com.example.demo.service.FacilityScoreService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

    private final FacilityScoreRepository facilityScoreRepository;
    private final PropertyRepository propertyRepository;
    private final RatingResultRepository ratingResultRepository;
    private final RatingLogRepository ratingLogRepository;

    // Requirement: Constructor Injection (No @Autowired on fields)
    public FacilityScoreServiceImpl(
            FacilityScoreRepository facilityScoreRepository,
            PropertyRepository propertyRepository,
            RatingResultRepository ratingResultRepository,
            RatingLogRepository ratingLogRepository) {
        this.facilityScoreRepository = facilityScoreRepository;
        this.propertyRepository = propertyRepository;
        this.ratingResultRepository = ratingResultRepository;
        this.ratingLogRepository = ratingLogRepository;
    }

    @Override
    @Transactional
    public FacilityScore saveScore(Long propertyId, FacilityScore score) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // Step 1: Validate Scores (0-10)
        validateScores(score);

        score.setProperty(property);
        FacilityScore savedScore = facilityScoreRepository.save(score);

        // Step 2: Trigger Rating Calculation
        calculateAndSaveRating(property, savedScore);

        return savedScore;
    }

    private void validateScores(FacilityScore score) {
        if (isInvalid(score.getSchoolProximity()) || isInvalid(score.getHospitalProximity()) ||
            isInvalid(score.getTransportAccess()) || isInvalid(score.getSafetyScore())) {
            throw new RuntimeException("Scores must be between 0 and 10");
        }
    }

    private boolean isInvalid(Integer value) {
        return value == null || value < 0 || value > 10;
    }

    private void calculateAndSaveRating(Property property, FacilityScore score) {
        // Step 3: Weighted Calculation
        double finalRating = (score.getSchoolProximity() * 0.3) +
                             (score.getHospitalProximity() * 0.2) +
                             (score.getTransportAccess() * 0.2) +
                             (score.getSafetyScore() * 0.3);

        // Step 4: Determine Category
        String category;
        if (finalRating >= 8) category = "EXCELLENT";
        else if (finalRating >= 6) category = "GOOD";
        else if (finalRating >= 4) category = "AVERAGE";
        else category = "POOR";

        // Step 5: Save Result
        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(finalRating);
        result.setRatingCategory(category);
        ratingResultRepository.save(result);

        // Step 6: Log the event
        RatingLog log = new RatingLog();
        log.setProperty(property);
        log.setMessage("Rating generated: " + category + " (" + finalRating + ")");
        ratingLogRepository.save(log);
    }
}