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

import com.example.demo.entity.*;
import com.example.demo.repository.*;
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

    public RatingServiceImpl(RatingResultRepository ratingResultRepository,
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

        // FIXED: Using .orElseThrow() on the Optional result
        FacilityScore score = facilityScoreRepository.findByPropertyId(propertyId)
                .orElseThrow(() -> new RuntimeException("Facility score missing"));

        // Requirement calculation
        double finalRating = (score.getSchoolProximity() * 0.3) +
                             (score.getHospitalProximity() * 0.2) +
                             (score.getTransportAccess() * 0.2) +
                             (score.getSafetyScore() * 0.3);

        String category = (finalRating >= 8) ? "EXCELLENT" : 
                          (finalRating >= 6) ? "GOOD" : 
                          (finalRating >= 4) ? "AVERAGE" : "POOR";

        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(finalRating);
        result.setRatingCategory(category);
        
        RatingResult saved = ratingResultRepository.save(result);

        RatingLog log = new RatingLog();
        log.setProperty(property);
        log.setMessage("Rating generated: " + category);
        ratingLogRepository.save(log);

        return saved;
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