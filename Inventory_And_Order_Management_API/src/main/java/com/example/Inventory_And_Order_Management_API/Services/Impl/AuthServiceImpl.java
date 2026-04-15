package com.example.Inventory_And_Order_Management_API.Services.Impl;

import com.example.Inventory_And_Order_Management_API.DTO.AuthRequest;
import com.example.Inventory_And_Order_Management_API.DTO.AuthResponse;
import com.example.Inventory_And_Order_Management_API.Entity.User;
import com.example.Inventory_And_Order_Management_API.Exception.BadRequestException;
import com.example.Inventory_And_Order_Management_API.Exception.ResourceNotFoundException;
import com.example.Inventory_And_Order_Management_API.Repository.UserRepository;
import com.example.Inventory_And_Order_Management_API.Security.JwtUtil;
import com.example.Inventory_And_Order_Management_API.Services.AuthService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    public AuthServiceImpl(UserRepository userRepository,
                           PasswordEncoder passwordEncoder,
                           JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public User register(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public AuthResponse login(AuthRequest request) {

        User user = userRepository.findByEmailIgnoreCase(request.email())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new BadRequestException("Invalid credentials");
        }

        String token = jwtUtil.generateToken(user.getEmail());

        return new AuthResponse(token);
    }
}