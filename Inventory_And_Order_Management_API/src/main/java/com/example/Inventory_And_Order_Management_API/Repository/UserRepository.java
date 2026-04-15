package com.example.Inventory_And_Order_Management_API.Repository;

import com.example.Inventory_And_Order_Management_API.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailIgnoreCase(String email);
}