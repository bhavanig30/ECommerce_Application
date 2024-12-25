package com.ecommerce.bhsw.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.bhsw.JwtUtil;
import com.ecommerce.bhsw.services.AuthService;
import com.ecommerce.bhsw.models.User;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @Autowired
    private JwtUtil jwtUtil;
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        authService.register(user);
        return ResponseEntity.ok("User registered successfully");
    }
    
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        User validUser = null;
        if (user.getEmail() != null) {
            validUser = authService.loginWithEmail(user.getEmail());
        } else if (user.getPhone() != null) {
            validUser = authService.loginWithPhone(user.getPhone());
        }
        
        if (validUser != null) {
            String token = jwtUtil.generateToken(validUser.getEmail());
            return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(401).body("Invalid email/phone number.");
    }

}
