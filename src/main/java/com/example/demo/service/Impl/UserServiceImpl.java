package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // REGISTER USER
    @Override
    public User register(User user) {
        return userRepository.save(user);
    }

    // LOGIN USER (NO JWT, NO SECURITY)
    @Override
    public User login(String email, String password) {
        User user = userRepository.findByEmail(email);

        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    // GET USER BY ID (CRUD)
    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
