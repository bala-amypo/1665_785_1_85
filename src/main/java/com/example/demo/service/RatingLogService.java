// package com.example.demo.service;

// import com.example.demo.entity.RatingLog;
// import java.util.List;

// public interface RatingLogService {

//     RatingLog addLog(Long propertyId, String message);

//     List<RatingLog> getLogsByProperty(Long propertyId);
// }

package com.example.demo.service;

import com.example.demo.entity.RatingLog;
import java.util.List;

public interface RatingLogService {
    /**
     * Adds a new audit log for a specific property.
     */
    RatingLog addLog(Long propertyId, String message);

    /**
     * Retrieves all logs associated with a property.
     */
    List<RatingLog> getLogsByProperty(Long propertyId);
}