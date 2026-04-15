package com.example.Inventory_And_Order_Management_API.Controller;

import com.example.Inventory_And_Order_Management_API.DTO.OrderRequest;
import com.example.Inventory_And_Order_Management_API.DTO.OrderResponse;
import com.example.Inventory_And_Order_Management_API.Entity.Order;
import com.example.Inventory_And_Order_Management_API.Services.OrderService;
import org.springframework.data.domain.Page;

import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/user/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public Order placeOrder(@RequestBody OrderRequest request) {
        return orderService.placeOrder(request);
    }

    @PostMapping("/{id}/cancel")
    public Order cancelOrder(@PathVariable Long id) {
        return orderService.cancelOrder(id);
    }

    @GetMapping
    public Page<OrderResponse> getOrders(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        return orderService.getOrders(page, size);
    }
}