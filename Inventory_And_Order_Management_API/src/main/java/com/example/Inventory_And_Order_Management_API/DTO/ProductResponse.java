package com.example.Inventory_And_Order_Management_API.DTO;

import java.util.List;

public record ProductResponse(
        Long id,
        String name,
        String description,
        Double price,
        Integer stock,
        String status,
        List<String> categories
) {}