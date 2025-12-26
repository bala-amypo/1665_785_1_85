// package com.example.demo.service;
// import com.example.demo.entity.User;

// public interface UserService {

//     User register(User user);

//     User login(String email, String password);

//     User getUserById(Long id);
// }

package com.example.demo.service;

import com.example.demo.entity.User;
import java.util.Optional;

public interface UserService {
    User register(User user);
    // FIX: Change return type to Optional<User> to match the implementation
    Optional<User> findByEmail(String email);
}