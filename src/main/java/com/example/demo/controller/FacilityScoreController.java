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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/scores")
@Tag(name = "Facility Score Controller")
public class FacilityScoreController {
    private final FacilityScoreService facilityScoreService;

    public FacilityScoreController(FacilityScoreService facilityScoreService) {
        this.facilityScoreService = facilityScoreService;
    }

    @PostMapping("/{propertyId}")
    @Operation(summary = "Submit facility score for a property")
    public FacilityScore submitScore(@PathVariable Long propertyId, @RequestBody FacilityScore score) {
        return facilityScoreService.addScore(propertyId, score);
    }

    @GetMapping("/{propertyId}")
    @Operation(summary = "Fetch score for a property")
    public FacilityScore getScore(@PathVariable Long propertyId) {
        return facilityScoreService.getScoreByProperty(propertyId);
    }
}
