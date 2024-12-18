package com.ecommerce.bhsw.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bhsw.models.Product;
import java.util.*;

public interface ProductRepository extends JpaRepository<Product, Long>{
    List<Product> findByNameContainingOrDescriptionContaining(String nameKeyword, String descriptionKeyword);
    List<Product> findBysellerId(Long sellerId);

}
