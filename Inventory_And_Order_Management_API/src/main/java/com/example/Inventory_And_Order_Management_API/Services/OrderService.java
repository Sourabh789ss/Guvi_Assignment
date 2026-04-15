package com.example.Inventory_And_Order_Management_API.Services;

import com.example.Inventory_And_Order_Management_API.DTO.OrderRequest;
import com.example.Inventory_And_Order_Management_API.DTO.OrderResponse;
import com.example.Inventory_And_Order_Management_API.Entity.Category;
import com.example.Inventory_And_Order_Management_API.Entity.Order;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OrderService {

    Order placeOrder(OrderRequest request);

    Order cancelOrder(Long orderId);

    Page<OrderResponse> getOrders(int page, int size);
}