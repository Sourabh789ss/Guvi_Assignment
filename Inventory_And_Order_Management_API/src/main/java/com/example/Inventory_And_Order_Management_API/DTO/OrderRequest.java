package com.example.Inventory_And_Order_Management_API.DTO;

import java.util.List;

public record OrderRequest(
        List<OrderItemRequest> items
) {}
