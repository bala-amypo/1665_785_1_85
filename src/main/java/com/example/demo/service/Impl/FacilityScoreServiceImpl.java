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
package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

    private final FacilityScoreRepository facilityScoreRepository;
    private final PropertyRepository propertyRepository;

    public FacilityScoreServiceImpl(FacilityScoreRepository facilityScoreRepository, PropertyRepository propertyRepository) {
        this.facilityScoreRepository = facilityScoreRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public FacilityScore addScore(Long propertyId, FacilityScore score) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));

        // Validation Rule: Scores must be between 0 and 10
        validateScoreRange(score.getSchoolProximity());
        validateScoreRange(score.getHospitalProximity());
        validateScoreRange(score.getTransportAccess());
        validateScoreRange(score.getSafetyScore());

        score.setProperty(property);
        return facilityScoreRepository.save(score);
    }

    private void validateScoreRange(Integer val) {
        if (val == null || val < 0 || val > 10) {
            throw new RuntimeException("Validation failed: Scores must be between 0 and 10");
        }
    }

    @Override
    public FacilityScore getScoreByProperty(Long propertyId) {
        return facilityScoreRepository.findByPropertyId(propertyId);
    }
}