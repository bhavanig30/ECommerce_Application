package com.ecommerce.bhsw.controller;

import com.ecommerce.bhsw.models.Cart;
import com.ecommerce.bhsw.models.Product;
import com.ecommerce.bhsw.models.User;
import com.ecommerce.bhsw.repository.CartRepository;
import com.ecommerce.bhsw.repository.ProductRepository;
import com.ecommerce.bhsw.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;
    @CrossOrigin(origins = "http://127.0.0.1:5500") 

    @PostMapping("/add/{productId}")
    public ResponseEntity<String> addToCart(@PathVariable Long productId, HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            return ResponseEntity.status(401).body("User not logged in!");
        }

        Optional<Product> product = productRepository.findById(productId);
        Optional<User> user = userRepository.findById(userId);

        if (product.isPresent() && user.isPresent()) {
            Cart cart = new Cart();
            cart.setUser(user.get());
            cart.setProduct(product.get());
            cartRepository.save(cart);
            return ResponseEntity.ok("Product added to cart!");
        }

        return ResponseEntity.status(404).body("Product or User not found!");
    }

    @GetMapping("/my-cart")
    public List<Cart> getUserCart(HttpSession session) {
        Long userId = (Long) session.getAttribute("userId");
        if (userId == null) {
            throw new IllegalStateException("User not logged in.");
        }

        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalStateException("User not found."));
        return cartRepository.findByUser(user);
    }

    @DeleteMapping("/remove/{cartId}")
    public ResponseEntity<String> removeFromCart(@PathVariable Long cartId) {
        if (cartRepository.existsById(cartId)) {
            cartRepository.deleteById(cartId);
            return ResponseEntity.ok("Product removed from cart!");
        } else {
            return ResponseEntity.status(404).body("Cart item not found!");
        }
    }
}
