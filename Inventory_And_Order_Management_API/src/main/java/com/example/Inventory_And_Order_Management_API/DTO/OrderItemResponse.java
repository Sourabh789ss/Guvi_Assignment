package com.example.Inventory_And_Order_Management_API.DTO;

public record OrderItemResponse(
        Long productId,
        String productName,
        Integer quantity,
        Double price
) {}
