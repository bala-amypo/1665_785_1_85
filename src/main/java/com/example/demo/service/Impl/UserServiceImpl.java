// package com.example.demo.service;
// import com.example.demo.entity.User;
// import com.example.demo.repository.UserRepository;
// import org.springframework.stereotype.Service;

// @Service
// public class UserServiceImpl implements UserService {

//     private final UserRepository userRepository;

//     public UserServiceImpl(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     @Override
//     public User register(User user) {
// return userRepository.save(user);
//     }

//     @Override
//     public User login(String email, String password) {
//         User user = userRepository.findByEmail(email);

//         if (user != null && user.getPassword().equals(password)) {
//             return user;
//         }
//         return null;
//     }

//     @Override
//     public User getUserById(Long id) {
//         return userRepository.findById(id).orElse(null);
//     }
// }

package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    // Requirement: Constructor Injection
    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            // Requirement: Message must contain "Email already in use"
            throw new RuntimeException("Validation failed: Email already in use");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }
}
