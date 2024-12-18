package com.ecommerce.bhsw.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.ecommerce.bhsw.models.Seller;
import com.ecommerce.bhsw.repository.SellerRepository;
import jakarta.servlet.http.HttpSession;


@CrossOrigin(origins = "http://127.0.0.1:5500") 
@RestController
@RequestMapping("/sellers")
public class SellerController {
    @Autowired
    private SellerRepository sellerRepository;
   

    @PostMapping("/login")
    public String loginSeller(@RequestBody Seller loginDetails,HttpSession session) {
        Optional<Seller> seller = sellerRepository.findByphonenumber(loginDetails.getPhonenumber());
        Optional<Seller> seller1 = sellerRepository.findByemail(loginDetails.getemail());
    

        if ((seller.isPresent() || seller1.isPresent())  && (seller.orElse(seller1.get()).getpassword().equals(loginDetails.getpassword()))) {
            session.setAttribute("sellerId", seller.orElse(seller1.get()).getId());
            return "Login successful!";
        }
        return "Invalid Login.";
    }
    @PostMapping("/register")
    public String registerSeller(@RequestBody Seller newSeller) {
        // Check if the phone number or email already exists
        if (sellerRepository.findByphonenumber(newSeller.getPhonenumber()).isPresent()) {
            return "Phone number already registered!";
        }

        if (sellerRepository.findByemail(newSeller.getemail()).isPresent()) {
            return "Email already registered!";
        }

        // Save the new user to the database
        sellerRepository.save(newSeller);
        return "User registered successfully!";
    }
    @GetMapping("/logout")
    public String logoutSeller(HttpSession session) {
        session.invalidate(); // Clear the session
        return "Logout successful!";
    }

}
