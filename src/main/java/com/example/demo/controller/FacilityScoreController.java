// package com.example.demo.controller;

// import com.example.demo.entity.FacilityScore;
// import com.example.demo.service.FacilityScoreService;

// import org.springframework.web.bind.annotation.*;

// @RestController
// @RequestMapping("/scores")
// public class FacilityScoreController {

//     private final FacilityScoreService facilityScoreService;

//     public FacilityScoreController(FacilityScoreService facilityScoreService) {
//         this.facilityScoreService = facilityScoreService;
//     }

//     @PostMapping("/{propertyId}")
//     public FacilityScore addScore(@PathVariable Long propertyId, @RequestBody FacilityScore score) {
//         return facilityScoreService.addScore(propertyId, score);
//     }


//     @GetMapping("/{propertyId}")
//     public FacilityScore getScore(@PathVariable Long propertyId) {
//         return facilityScoreService.getScoreByProperty(propertyId);
//     }
// }


package com.example.demo.controller;

import com.example.demo.entity.FacilityScore;
import com.example.demo.service.FacilityScoreService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/facility-scores")
public class FacilityScoreController {

    private final FacilityScoreService facilityScoreService;

    public FacilityScoreController(FacilityScoreService facilityScoreService) {
        this.facilityScoreService = facilityScoreService;
    }

    @PostMapping("/{propertyId}")
    public ResponseEntity<FacilityScore> addScore(@PathVariable Long propertyId, 
                                                 @RequestBody FacilityScore score) {
        return ResponseEntity.ok(facilityScoreService.addScore(propertyId, score));
    }

    @GetMapping("/property/{propertyId}")
    public ResponseEntity<FacilityScore> getScore(@PathVariable Long propertyId) {
        return ResponseEntity.ok(facilityScoreService.getScoreByProperty(propertyId));
    }
}