package com.ecommerce.bhsw.controller;

import com.ecommerce.bhsw.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired; // For dependency injection
import org.springframework.web.bind.annotation.*; // For REST controller annotations

import java.util.Optional;
import com.ecommerce.bhsw.models.User;;

@CrossOrigin(origins = "http://127.0.0.1:5500") 
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @PostMapping("/login")
    public String loginUser(@RequestBody User loginDetails,HttpSession session) {
        Optional<User> user = userRepository.findByphonenumber(loginDetails.getPhonenumber());
        Optional<User> user1 = userRepository.findByemail(loginDetails.getemail());

        if (user.isPresent()) {
            session.setAttribute("userId", user.get().getId());
            return "Login successful!";
        } else if (user1.isPresent()) {
            session.setAttribute("userId", user1.get().getId());
            return "Login successful!";
        }
    
        return "Invalid email or password.";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User newUser) {
        // Check if the phone number or email already exists
        if (userRepository.findByphonenumber(newUser.getPhonenumber()).isPresent()) {
            return "Phone number already registered!";
        }

        if (userRepository.findByemail(newUser.getemail()).isPresent()) {
            return "Email already registered!";
        }

        // Save the new user to the database
        userRepository.save(newUser);
        return "User registered successfully!";
    }

}
