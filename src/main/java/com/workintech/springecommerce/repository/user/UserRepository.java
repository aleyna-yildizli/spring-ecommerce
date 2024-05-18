package com.workintech.springecommerce.repository.user;

import com.workintech.springecommerce.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long>{
    @Query("SELECT u FROM User u WHERE u.email = :email")
   Optional<User> findUserByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.id = :id")
    Optional<User> findUserById(Long id);
}
