// package com.example.demo.service;

// import com.example.demo.entity.Property;
// import java.util.List;

// public interface PropertyService {

//     Property addProperty(Property property);

//     List<Property> getAllProperties();
// }

package com.example.demo.service;

import com.example.demo.entity.Property;
import java.util.List;

public interface PropertyService {
    Property addProperty(Property property);
    List<Property> getAllProperties();
    
    // Add these to match your Implementation and fix the error
    List<Property> findByCity(String city);
    List<Property> findByCityHql(String city);
}