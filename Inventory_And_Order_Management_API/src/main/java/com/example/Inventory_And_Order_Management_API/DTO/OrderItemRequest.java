package com.example.Inventory_And_Order_Management_API.DTO;

public record OrderItemRequest(
        Long productId,
        Integer quantity
) {}
