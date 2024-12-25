package com.ecommerce.bhsw.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ecommerce.bhsw.repository.UserRepository;
import com.ecommerce.bhsw.models.User;

@Service

public class AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public User loginWithEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public User loginWithPhone(String phone) {
        return userRepository.findByPhone(phone);
    }

}
