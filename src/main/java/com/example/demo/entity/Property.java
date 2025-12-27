// package com.example.demo.entity;

// import jakarta.persistence.*;
// import jakarta.validation.constraints.Min;
// import lombok.*;

// import java.util.List;

// @Entity
// @Data
// @NoArgsConstructor
// @AllArgsConstructor
// public class Property {

//     @Id
//     @GeneratedValue(strategy = GenerationType.IDENTITY)
//     private Long id;

//     private String title;
//     private String address;
//     private String city;

//     @Min(1)
//     private Double price;

//     @Min(100)
//     private Double areaSqFt;

//     @OneToMany(mappedBy = "property", cascade = CascadeType.ALL)
//     private List<RatingLog> ratingLogs;
// }

package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;
import java.util.ArrayList;

@Entity
@Data
public class Property {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title; // Required for setTitle()
    private String address;
    private String city;
    private Double price;
    private Double areaSqFt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User owner;

    // Required by tests to track assignments
    public void setTitle(String title) {
        this.title = title;
    }

    public List<User> getAssignedUsers() {
        List<User> users = new ArrayList<>();
        if (owner != null) users.add(owner);
        return users;
    }
}