package com.example.Inventory_And_Order_Management_API.Repository;


import com.example.Inventory_And_Order_Management_API.Entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
