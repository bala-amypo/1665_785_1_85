package com.example.demo.service;

import com.example.demo.entity.FacilityScore;
import com.example.demo.entity.Property;
import com.example.demo.repository.FacilityScoreRepository;
import com.example.demo.repository.PropertyRepository;
import org.springframework.stereotype.Service;

@Service
public class FacilityScoreServiceImpl implements FacilityScoreService {

    private final FacilityScoreRepository scoreRepository;
    private final PropertyRepository propertyRepository;

    public FacilityScoreServiceImpl(FacilityScoreRepository scoreRepository,
                                    PropertyRepository propertyRepository) {
        this.scoreRepository = scoreRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public FacilityScore addScore(Long propertyId, FacilityScore score) {
        Property property = propertyRepository.findById(propertyId).orElseThrow();
        score.setProperty(property);
        return scoreRepository.save(score);
    }

    @Override
    public FacilityScore getScoreByProperty(Long propertyId) {
        return scoreRepository.findByPropertyId(propertyId);
    }
}
