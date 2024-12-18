package com.ecommerce.bhsw.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import com.ecommerce.bhsw.models.Product;
import com.ecommerce.bhsw.repository.ProductRepository;
import java.util.*;


@CrossOrigin(origins = "http://127.0.0.1:5500") 
@RestController
public class SearchController {

@Autowired
private ProductRepository productRepository;

@GetMapping("/search")
public List<Product> searchProducts(@RequestParam String keyword) {
    return productRepository.findByNameContainingOrDescriptionContaining(keyword, keyword);
}

}
