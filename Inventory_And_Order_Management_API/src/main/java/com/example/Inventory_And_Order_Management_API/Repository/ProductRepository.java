package com.example.Inventory_And_Order_Management_API.Repository;

import com.example.Inventory_And_Order_Management_API.Entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    // Filter by category (JOIN)
    Page<Product> findByCategories_Name(String category, Pageable pageable);

    // Search by name
    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);

    // Low stock
    Page<Product> findByStockLessThan(int threshold, Pageable pageable);
}