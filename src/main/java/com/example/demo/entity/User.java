
// package com.example.demo.entity;


// import jakarta.persistence.*;
// import lombok.Getter;
// import lombok.Setter;

// @Entity
// @Getter
// @Setter
// public class User {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;
    
//     private String name;
//     private String email;
//     private String password;
// }

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String password;
    private String role;

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
    private List<Property> assignedProperties = new ArrayList<>();

    // Required for Step 4.1 and Integration Tests
    public List<Property> getAssignedProperties() {
        return assignedProperties;
    }
}