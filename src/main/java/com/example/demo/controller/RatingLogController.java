// package com.example.demo.controller;
// import com.example.demo.entity.RatingLog;
// import com.example.demo.service.RatingLogService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/logs")
// public class RatingLogController {

//     private final RatingLogService ratingLogService;

//     public RatingLogController(RatingLogService ratingLogService) {
//         this.ratingLogService = ratingLogService;
//     }

//     @PostMapping("/{propertyId}")
//     public RatingLog addLog(@PathVariable Long propertyId,
//                             @RequestBody String message) {
//         return ratingLogService.addLog(propertyId, message);
//     }

//     @GetMapping("/property/{propertyId}")
//     public List<RatingLog> getLogs(@PathVariable Long propertyId) {
//         return ratingLogService.getLogsByProperty(propertyId);
//     }
// }

// package com.example.demo.controller;

// import com.example.demo.entity.RatingLog;
// import com.example.demo.service.RatingLogService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.tags.Tag;
// import org.springframework.web.bind.annotation.*;
// import java.util.List;

// @RestController
// @RequestMapping("/logs")
// @Tag(name = "Rating Log Controller")
// public class RatingLogController {

//     private final RatingLogService logService;

//     public RatingLogController(RatingLogService logService) {
//         this.logService = logService;
//     }

//     @PostMapping("/{propertyId}")
//     @Operation(summary = "Add a log entry for a property")
//     public RatingLog addLog(@PathVariable Long propertyId, @RequestBody String message) {
//         return logService.addLog(propertyId, message);
//     }

//     @GetMapping("/property/{propertyId}")
//     @Operation(summary = "View logs for a specific property")
//     public List<RatingLog> viewLogs(@PathVariable Long propertyId) {
//         return logService.getLogsByProperty(propertyId);
//     }
// }

package com.example.demo.controller;

import com.example.demo.entity.RatingLog;
import com.example.demo.repository.RatingLogRepository;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.entity.Property;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rating-logs")
@Tag(name = "Rating Log Controller", description = "Endpoints for viewing rating audit logs")
public class RatingLogController {

    private final RatingLogRepository ratingLogRepository;
    private final PropertyRepository propertyRepository;

    public RatingLogController(RatingLogRepository ratingLogRepository, PropertyRepository propertyRepository) {
        this.ratingLogRepository = ratingLogRepository;
        this.propertyRepository = propertyRepository;
    }

    @GetMapping("/property/{propertyId}")
    @Operation(summary = "Get audit logs for a specific property")
    public ResponseEntity<List<RatingLog>> getLogsByProperty(@PathVariable Long propertyId) {
        Property property = propertyRepository.findById(propertyId)
                .orElseThrow(() -> new RuntimeException("Property not found"));
        return ResponseEntity.ok(ratingLogRepository.findByProperty(property));
    }
}