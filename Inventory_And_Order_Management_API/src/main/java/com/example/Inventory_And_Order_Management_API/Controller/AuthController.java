package com.example.Inventory_And_Order_Management_API.Controller;

import com.example.Inventory_And_Order_Management_API.DTO.AuthRequest;
import com.example.Inventory_And_Order_Management_API.DTO.AuthResponse;
import com.example.Inventory_And_Order_Management_API.Entity.User;

import com.example.Inventory_And_Order_Management_API.Services.AuthService;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }
}