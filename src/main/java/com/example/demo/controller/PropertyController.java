// package com.example.demo.controller;

// import com.example.demo.entity.Property;
// import com.example.demo.service.PropertyService;
// import org.springframework.web.bind.annotation.*;

// import java.util.List;

// @RestController
// @RequestMapping("/properties")
// public class PropertyController {

//     private final PropertyService propertyService;

//     public PropertyController(PropertyService propertyService) {
//         this.propertyService = propertyService;
//     }

//     @PostMapping
//     public Property addProperty(@RequestBody Property property) {
//         return propertyService.addProperty(property);
//     }

//     @GetMapping
//     public List<Property> getAllProperties() {
//         return propertyService.getAllProperties();
//     }
// }

package com.example.demo.controller;

import com.example.demo.entity.Property;
import com.example.demo.service.PropertyService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/properties")
@Tag(name = "Property Controller")
public class PropertyController {

    private final PropertyService propertyService;

    public PropertyController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @PostMapping("/")
    @Operation(summary = "Add a new property")
    public Property addProperty(@RequestBody Property property) {
        return propertyService.addProperty(property);
    }

    @GetMapping("/")
    @Operation(summary = "List all properties")
    public List<Property> listProperties() {
        return propertyService.getAllProperties();
    }
}