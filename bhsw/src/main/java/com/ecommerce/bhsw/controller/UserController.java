package com.ecommerce.bhsw.controller;
import com.ecommerce.bhsw.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired; // For dependency injection
import org.springframework.web.bind.annotation.*; // For REST controller annotations

import java.util.Optional;
import com.ecommerce.bhsw.models.User;;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserRepository userRepository;
    @CrossOrigin(origins = "http://127.0.0.1:5500") 

    @PostMapping("/login")
    public String loginUser(@RequestBody User loginDetails) {
        Optional<User> user = userRepository.findByphonenumber(loginDetails.getPhonenumber());
        Optional<User> user1 = userRepository.findByemail(loginDetails.getemail());

        if (user.isPresent() || user1.isPresent()) {
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
