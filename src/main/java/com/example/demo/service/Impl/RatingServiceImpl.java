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
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingResultRepository;
import com.example.demo.service.RatingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingResultRepository ratingResultRepository;
    private final FacilityScoreRepository facilityScoreRepository;
    private final PropertyRepository propertyRepository;

    public RatingServiceImpl(RatingResultRepository ratingResultRepository, 
                             FacilityScoreRepository facilityScoreRepository,
                             PropertyRepository propertyRepository) {
        this.ratingResultRepository = ratingResultRepository;
        this.facilityScoreRepository = facilityScoreRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    @Transactional
    public RatingResult generateRating(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        FacilityScore scores = facilityScoreRepository.findByProperty(property)
                .orElseThrow(() -> new RuntimeException("Facility scores not found"));

        // Using your specific entity field names:
        // schoolProximity, hospitalProximity, transportAccess, safetyScore
        double finalRating = (scores.getSchoolProximity() * 0.3) +
                             (scores.getHospitalProximity() * 0.2) +
                             (scores.getTransportAccess() * 0.2) +
                             (scores.getSafetyScore() * 0.3);

        RatingResult result = ratingResultRepository.findByProperty(property)
                .orElse(new RatingResult());
        
        result.setProperty(property);
        result.setFinalRating(finalRating);

        return ratingResultRepository.save(result);
    }

    @Override
    public List<RatingResult> getAllRatings() {
        return ratingResultRepository.findAll();
    }

    @Override
    public RatingResult getRating(Long propertyId) {
        return ratingResultRepository.findByPropertyId(propertyId)
                .orElseThrow(() -> new RuntimeException("Rating not found"));
    }

    @Override
    public RatingResult getRatingByProperty(Long propertyId) {
        return getRating(propertyId);
    }
}