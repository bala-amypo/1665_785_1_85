// package com.example.demo.service;

// import com.example.demo.entity.RatingResult;

// public interface RatingService {

//     RatingResult generateRating(Long propertyId);

//     RatingResult getRating(Long propertyId);
// }

package com.example.demo.service;

import com.example.demo.entity.RatingResult;

public interface RatingService {
    // Corrected interface name to match filename
    RatingResult generateRating(Long propertyId); 
    RatingResult getRating(Long propertyId); // Required by integration tests
}