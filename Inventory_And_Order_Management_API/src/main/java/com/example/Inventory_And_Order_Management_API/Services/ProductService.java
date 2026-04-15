package com.example.Inventory_And_Order_Management_API.Services;

import com.example.Inventory_And_Order_Management_API.DTO.ProductRequest;
import com.example.Inventory_And_Order_Management_API.DTO.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    ProductResponse update(Long id, ProductRequest request);

    Page<ProductResponse> getProducts(
            String category,
            String search,
            Integer lowStock,
            Pageable pageable
    );

    void delete(Long id);
}
