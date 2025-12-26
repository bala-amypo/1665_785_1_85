// package com.example.demo.service;

// import com.example.demo.entity.RatingResult;

// public interface RatingService {

//     RatingResult generateRating(Long propertyId);

//     RatingResult getRating(Long propertyId);
// }


package com.example.demo.service;

import com.example.demo.entity.RatingResult;
import java.util.List;

public interface RatingService {
    RatingResult generateRating(Long propertyId);
    List<RatingResult> getAllRatings();
    // Use this exact name to match the implementation we are about to fix
    RatingResult getRatingByProperty(Long propertyId); 
}