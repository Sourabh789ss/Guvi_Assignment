package com.example.Inventory_And_Order_Management_API.Services;

import com.example.Inventory_And_Order_Management_API.DTO.AuthRequest;
import com.example.Inventory_And_Order_Management_API.DTO.AuthResponse;
import com.example.Inventory_And_Order_Management_API.DTO.ProductRequest;
import com.example.Inventory_And_Order_Management_API.DTO.ProductResponse;
import com.example.Inventory_And_Order_Management_API.Entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthService {

    User register(User user);

    AuthResponse login(AuthRequest request);
}
