package com.example.Inventory_And_Order_Management_API.DTO;

public record AuthRequest(
        String email,
        String password
) {}