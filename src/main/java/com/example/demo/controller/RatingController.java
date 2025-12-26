// package com.example.demo.controller;

// import com.example.demo.entity.RatingResult;
// import com.example.demo.service.RatingService;
// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/ratings")
// public class RatingController {

//     private final RatingService ratingService;

//     public RatingController(RatingService ratingService) {
//         this.ratingService = ratingService;
//     }

//     @PostMapping("/generate/{propertyId}")
//     public RatingResult generateRating(@PathVariable Long propertyId) {
//         return ratingService.generateRating(propertyId);
//     }

//     @GetMapping("/property/{propertyId}")
//     public RatingResult getRating(@PathVariable Long propertyId) {
//         return ratingService.getRating(propertyId);
//     }
// }
package com.example.demo.controller;

import com.example.demo.entity.RatingResult;
import com.example.demo.service.RatingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
@Tag(name = "Rating Controller", description = "Endpoints for generating and viewing property ratings")
public class RatingController {

    private final RatingService ratingService;

    // Requirement: Constructor Injection
    public RatingController(RatingService ratingService) {
        this.ratingService = ratingService;
    }

    @PostMapping("/{propertyId}/generate")
    @Operation(summary = "Generate a rating for a property based on facility scores")
    public ResponseEntity<RatingResult> generateRating(@PathVariable Long propertyId) {
        return ResponseEntity.ok(ratingService.generateRating(propertyId));
    }

    @GetMapping
    @Operation(summary = "Get all generated ratings")
    public ResponseEntity<List<RatingResult>> getAllRatings() {
        return ResponseEntity.ok(ratingService.getAllRatings());
    }

    @GetMapping("/property/{propertyId}")
    @Operation(summary = "Get the specific rating result for a property")
    public ResponseEntity<RatingResult> getRatingByProperty(@PathVariable Long propertyId) {
        // This calls the method we just fixed in the RatingService interface
        return ResponseEntity.ok(ratingService.getRatingByProperty(propertyId));
    }
}