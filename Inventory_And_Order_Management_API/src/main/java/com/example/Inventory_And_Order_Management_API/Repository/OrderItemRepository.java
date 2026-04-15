package com.example.Inventory_And_Order_Management_API.Repository;

import com.example.Inventory_And_Order_Management_API.Entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}