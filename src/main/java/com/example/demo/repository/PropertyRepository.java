// package com.example.demo.repository;

// import com.example.demo.entity.Property;
// import org.springframework.data.jpa.repository.*;
// import org.springframework.data.repository.query.Param;
// import java.util.List;

// public interface PropertyRepository extends JpaRepository<Property, Long> {

//     @Query("SELECT p FROM Property p WHERE p.city = :city")
//     List<Property> findByCityHql(@Param("city") String city);

//     List<Property> findByCity(String city);
// }

package com.example.demo.repository;

import com.example.demo.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
@Repository
public interface PropertyRepository extends JpaRepository<Property, Long> {
    @Query("SELECT p FROM Property p WHERE p.city = :city")
    List<Property> findByCityHql(String city);
    
    List<Property> findByCity(String city);
}

