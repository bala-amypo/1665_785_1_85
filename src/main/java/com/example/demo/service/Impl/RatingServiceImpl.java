package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.entity.RatingResult;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingResultRepository;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {

    private final FacilityScoreRepository scoreRepository;
    private final RatingResultRepository resultRepository;
    private final PropertyRepository propertyRepository;
    private final RatingLogService ratingLogService;

    public RatingServiceImpl(FacilityScoreRepository scoreRepository,RatingResultRepository resultRepository, PropertyRepository propertyRepository,RatingLogService ratingLogService) {
    this.scoreRepository = scoreRepository;
        this.resultRepository = resultRepository;
        this.propertyRepository = propertyRepository;
        this.ratingLogService = ratingLogService;
    }

    @Override
    public RatingResult generateRating(Long propertyId) {
        FacilityScore score = scoreRepository.findByPropertyId(propertyId);

        double avg = (score.getSchoolProximity() + score.getHospitalProximity()+ score.getTransportAccess()+ score.getSafetyScore()) / 4.0;

        String category =avg < 4 ? "POOR" :
                avg < 6 ? "AVERAGE" :
                avg < 8 ? "GOOD" : "EXCELLENT";

        Property property = propertyRepository.findById(propertyId).orElseThrow();

        RatingResult result = new RatingResult();
        result.setProperty(property);
        result.setFinalRating(avg);
        result.setRatingCategory(category);

        ratingLogService.addLog(propertyId, "Rating generated: " + category);

        return resultRepository.save(result);
    }

    @Override
    public RatingResult getRating(Long propertyId) {
        return resultRepository.findByPropertyId(propertyId);
    }
}
