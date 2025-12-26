// package com.example.demo.service;
// import com.example.demo.entity.Property;
// import com.example.demo.repository.PropertyRepository;
// import org.springframework.stereotype.Service;
// import java.util.List;

// @Service
// public class PropertyServiceImpl implements PropertyService {

//     private final PropertyRepository propertyRepository;

//     public PropertyServiceImpl(PropertyRepository propertyRepository) {
//         this.propertyRepository = propertyRepository;
//     }

//     @Override
//     public Property addProperty(Property property) {
//         return propertyRepository.save(property);
//     }

//     @Override
//     public List<Property> getAllProperties() {
//         return propertyRepository.findAll();
//     }
// }

package com.example.demo.service;

import com.example.demo.entity.Property;
import com.example.demo.repository.PropertyRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    // Requirement: Must use Constructor Injection
    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Property addProperty(Property property) {
        // Validation Rules: price > 0 and area >= 100
        if (property.getPrice() == null || property.getPrice() <= 0) {
            throw new RuntimeException("Validation failed: Price must be greater than 0");
        }
        if (property.getAreaSqFt() == null || property.getAreaSqFt() < 100) {
            throw new RuntimeException("Validation failed: Area must be at least 100 sq ft");
        }
        return propertyRepository.save(property);
    }

    @Override
    public List<Property> getAllProperties() {
        return propertyRepository.findAll();
    }
}
