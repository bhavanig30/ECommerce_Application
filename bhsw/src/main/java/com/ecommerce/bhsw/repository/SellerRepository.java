package com.ecommerce.bhsw.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.bhsw.models.Seller;


public interface SellerRepository  extends JpaRepository<Seller,Long>{

Optional<Seller> findByphonenumber(String phonenumber);
Optional<Seller> findByemail(String email);
Optional<Seller> findBypassword(String password);

}
