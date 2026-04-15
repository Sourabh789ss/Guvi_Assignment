package com.example.Inventory_And_Order_Management_API.Util;

import com.example.Inventory_And_Order_Management_API.DTO.OrderItemResponse;
import com.example.Inventory_And_Order_Management_API.DTO.OrderResponse;
import com.example.Inventory_And_Order_Management_API.DTO.ProductResponse;
import com.example.Inventory_And_Order_Management_API.Entity.Category;
import com.example.Inventory_And_Order_Management_API.Entity.Order;
import com.example.Inventory_And_Order_Management_API.Entity.Product;

import java.util.List;

public class MapperUtil {

    public static ProductResponse toProductResponse(Product product) {
        List<String> categoryNames = product.getCategories()
                .stream()
                .map(Category::getName)
                .toList();

        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getStock(),
                product.getStatus().name(),
                categoryNames
        );
    }

    public static OrderResponse toOrderResponse(Order order) {

        List<OrderItemResponse> items = order.getItems().stream()
                .map(item -> new OrderItemResponse(
                        item.getProduct().getId(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice()
                ))
                .toList();

        return new OrderResponse(
                order.getId(),
                order.getStatus().name(),
                order.getCreatedAt().toString(),
                items
        );
    }
}

