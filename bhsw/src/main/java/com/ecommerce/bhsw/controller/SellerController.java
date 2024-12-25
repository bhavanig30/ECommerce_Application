package com.ecommerce.bhsw.controller;

import com.ecommerce.bhsw.models.Seller;
import com.ecommerce.bhsw.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sellers")
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class SellerController {

    @Autowired
    private SellerRepository sellerRepository;
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/register")
    public ResponseEntity<Seller> registerSeller(@RequestBody Seller seller) {
        Seller savedSeller = sellerRepository.save(seller);
        return ResponseEntity.ok(savedSeller);
    }
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/login")
    public ResponseEntity<Seller> loginSeller(@RequestBody Seller loginDetails) {
        Seller seller = sellerRepository.findByEmail(loginDetails.getemail())
                .orElseThrow(() -> new RuntimeException("Invalid email or password"));
        if (!seller.getpassword().equals(loginDetails.getpassword())) {
            throw new RuntimeException("Invalid email or password");
        }
        return ResponseEntity.ok(seller);
    }
}
