package com.ecommerce.bhsw.repository;
import com.ecommerce.bhsw.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Long>{
    Optional<User> findByphonenumber(String phonenumber);
    Optional<User> findByemail(String email);
}
