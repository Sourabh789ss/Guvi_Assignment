package com.example.Inventory_And_Order_Management_API.DTO;

import java.util.List;

public record OrderResponse(
        Long id,
        String status,
        String createdAt,
        List<OrderItemResponse> items
) {}
