// package com.example.demo.service;
// import com.example.demo.entity.Property;
// import com.example.demo.entity.RatingLog;
// import com.example.demo.repository.PropertyRepository;
// import com.example.demo.repository.RatingLogRepository;
// import org.springframework.stereotype.Service;
// import java.util.List;

// @Service
// public class RatingLogServiceImpl implements RatingLogService {

//     private final RatingLogRepository logRepository;
//     private final PropertyRepository propertyRepository;

//     public RatingLogServiceImpl(RatingLogRepository logRepository,
//                                 PropertyRepository propertyRepository) {
//         this.logRepository = logRepository;
//         this.propertyRepository = propertyRepository;
//     }

//     @Override
//     public RatingLog addLog(Long propertyId, String message) {
//         Property property = propertyRepository.findById(propertyId).orElseThrow();

//         RatingLog log = new RatingLog();
//         log.setProperty(property);
//         log.setMessage(message);

//         return logRepository.save(log);
//     }

//     @Override
//     public List<RatingLog> getLogsByProperty(Long propertyId) {
//         return logRepository.findByPropertyId(propertyId);
//     }
// }


package com.example.demo.service.Impl;

import com.example.demo.entity.Property;
import com.example.demo.entity.RatingLog;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.repository.RatingLogRepository;
import com.example.demo.service.RatingLogService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class RatingLogServiceImpl implements RatingLogService {
    private final RatingLogRepository ratingLogRepository;
    private final PropertyRepository propertyRepository;

    public RatingLogServiceImpl(RatingLogRepository ratingLogRepository, PropertyRepository propertyRepository) {
        this.ratingLogRepository = ratingLogRepository;
        this.propertyRepository = propertyRepository;
    }

    @Override
    public RatingLog addLog(Long propertyId, String message) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        RatingLog log = new RatingLog();
        log.setProperty(property);
        log.setMessage(message);
        return ratingLogRepository.save(log);
    }

    @Override
    public List<RatingLog> getLogsByProperty(Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        return ratingLogRepository.findByProperty(property); // Ensure Repository has findByProperty
    }
}