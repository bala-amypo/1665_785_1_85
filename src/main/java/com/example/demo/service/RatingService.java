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
    // Note: The image says getRating, not getRatingByProperty
    RatingResult getRating(Long propertyId); 
}