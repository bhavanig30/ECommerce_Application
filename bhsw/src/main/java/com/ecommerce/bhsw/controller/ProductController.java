package com.ecommerce.bhsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.ecommerce.bhsw.models.Product;
import com.ecommerce.bhsw.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;
import java.util.*;



@CrossOrigin(origins = "http://127.0.0.1:5500") 
@RestController
@RequestMapping("/products")
public class ProductController {
@Autowired
private ProductRepository productRepository;

@PostMapping("/add")
public ResponseEntity<String> addProduct(@RequestBody Product product,HttpSession session) {


    Long sellerId = (Long) session.getAttribute("sellerId");
    if (sellerId == null) {
        return ResponseEntity.status(401).body("Unauthorized. Please log in first.");
    }

    // Associate the product with the seller
    product.setSellerId(sellerId);
    productRepository.save(product);
    return ResponseEntity.ok("Product added successfully!");
}

@DeleteMapping("/delete/{id}")
public ResponseEntity<String> deleteProduct(@PathVariable Long id) {
    if (productRepository.existsById(id)) {
        productRepository.deleteById(id);
        return ResponseEntity.ok("Product deleted successfully!");
    } else {
        return ResponseEntity.status(404).body("Product not found!");
    }
}
@PutMapping("/update/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
        return productRepository.findById(id).map(product -> {
            product.setName(updatedProduct.getName());
            product.setPrice(updatedProduct.getPrice());
            product.setSize(updatedProduct.getSize());
            product.setDescription(updatedProduct.getDescription());
            product.setColor(updatedProduct.getColor());
            product.setImageUrl(updatedProduct.getImageUrl());
            product.setCategory(updatedProduct.getCategory());

            productRepository.save(product);
            return ResponseEntity.ok("Product updated successfully!");
        }).orElse(ResponseEntity.status(404).body("Product not found!"));
    }
    @GetMapping("/my-products")
    public List<Product> getSellerProducts(HttpSession session) {
        Long sellerId = (Long) session.getAttribute("sellerId");
        if (sellerId == null) {
            throw new IllegalStateException("Seller not logged in.");
        }

        // Fetch products by seller ID
        return productRepository.findBysellerId(sellerId);
    }

}
