package com.ecommerce.bhsw.controller;

import com.ecommerce.bhsw.models.Product;
import com.ecommerce.bhsw.models.Seller;
import com.ecommerce.bhsw.repository.ProductRepository;
import com.ecommerce.bhsw.repository.SellerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SellerRepository sellerRepository;
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @PostMapping("/add/{sellerId}")
    public ResponseEntity<String> addProduct(@PathVariable Long sellerId, @RequestBody Product product) {
        Seller seller = sellerRepository.findById(sellerId).orElse(null);
        if (seller == null) {
            return ResponseEntity.badRequest().body("Seller not found");
        }
        product.setSeller(seller);
        productRepository.save(product);
        return ResponseEntity.ok("Product added successfully");
    }
    @CrossOrigin(origins = "http://127.0.0.1:5500")
    @GetMapping("/seller/{sellerId}")
    public ResponseEntity<List<Product>> getProductsBySeller(@PathVariable Long sellerId) {
        List<Product> products = productRepository.findBySellerId(sellerId);
        return ResponseEntity.ok(products);
    }
}
