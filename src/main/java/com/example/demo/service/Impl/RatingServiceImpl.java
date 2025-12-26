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

package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingResultRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private final RatingResultRepository ratingResultRepository;
    private final FacilityScoreRepository facilityScoreRepository;
    private final PropertyRepository propertyRepository;
    private final RatingLogService logService;

    public RatingServiceImpl(RatingResultRepository rr, FacilityScoreRepository fs, 
                             PropertyRepository pr, RatingLogService ls) {
        this.ratingResultRepository = rr;
        this.facilityScoreRepository = fs;
        this.propertyRepository = pr;
        this.logService = ls;
    }

    @Override
    public RatingResult generateRating(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        FacilityScore fs = facilityScoreRepository.findByPropertyId(propertyId);
        if (fs == null) {
            throw new RuntimeException("Facility scores not found for this property");
        }

        // Compute average finalRating
        double finalRating = (fs.getSchoolProximity() + fs.getHospitalProximity() + 
                             fs.getTransportAccess() + fs.getSafetyScore()) / 4.0;

        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(finalRating);
        
        // Category thresholds
        if (finalRating >= 8.0) result.setRatingCategory("EXCELLENT");
        else if (finalRating >= 6.0) result.setRatingCategory("GOOD");
        else if (finalRating >= 4.0) result.setRatingCategory("AVERAGE");
        else result.setRatingCategory("POOR");

        // Requirement: Log each scoring stage
        logService.addLog(propertyId, "Calculated average: " + finalRating + ". Category assigned: " + result.getRatingCategory());

        return ratingResultRepository.save(result);
    }

    @Override
    public RatingResult getRating(Long propertyId) {
        return ratingResultRepository.findByPropertyId(propertyId);
    }
}
