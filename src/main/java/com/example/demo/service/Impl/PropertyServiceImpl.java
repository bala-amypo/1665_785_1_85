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
package com.example.demo.service.Impl;

import com.example.demo.entity.Property;
import com.example.demo.repository.PropertyRepository;
import com.example.demo.service.PropertyService;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyServiceImpl(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @Override
    public Property addProperty(Property property) {
        // Requirement: Basic persistence for new properties
        return propertyRepository.save(property);
    }

    @Override
    public List<Property> getAllProperties() {
        // Requirement: Retrieve all for the dashboard
        return propertyRepository.findAll();
    }

    @Override
    public List<Property> findByCity(String city) {
        // Required by search tests (image_e91365.png)
        return propertyRepository.findByCity(city);
    }

    @Override
    public List<Property> findByCityHql(String city) {
        // Required for Step 0 HQL requirement
        return propertyRepository.findByCityHql(city);
    }
}