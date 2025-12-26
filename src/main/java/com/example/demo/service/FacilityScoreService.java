// package com.example.demo.service;

// import com.example.demo.entity.FacilityScore;

// public interface FacilityScoreService {

//     FacilityScore addScore(Long propertyId, FacilityScore score);

//     FacilityScore getScoreByProperty(Long propertyId);
// }
package com.example.demo.service;

import com.example.demo.entity.FacilityScore;

public interface FacilityScoreService {
    // Rename from saveScore to addScore to match the Controller call
    FacilityScore addScore(Long propertyId, FacilityScore score);
    FacilityScore getScoreByProperty(Long propertyId);
}