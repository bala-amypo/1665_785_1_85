// package com.example.demo.service;
// import com.example.demo.entity.User;

// public interface UserService {

//     User register(User user);

//     User login(String email, String password);

//     User getUserById(Long id);
// }
package com.example.demo.service;

import com.example.demo.entity.User;

public interface UserService {
    // Used for registration
    User register(User user);
    
    // Used for login to fetch user metadata
    User findByEmail(String email);
}