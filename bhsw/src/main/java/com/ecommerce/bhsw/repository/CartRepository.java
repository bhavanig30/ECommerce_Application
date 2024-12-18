package com.ecommerce.bhsw.repository;

import com.ecommerce.bhsw.models.Cart;
import com.ecommerce.bhsw.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByUser(User user);
}
