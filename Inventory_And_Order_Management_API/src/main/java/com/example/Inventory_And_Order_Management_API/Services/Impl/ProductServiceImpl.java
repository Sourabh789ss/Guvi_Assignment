package com.example.Inventory_And_Order_Management_API.Services.Impl;

import com.example.Inventory_And_Order_Management_API.DTO.ProductRequest;
import com.example.Inventory_And_Order_Management_API.DTO.ProductResponse;
import com.example.Inventory_And_Order_Management_API.Entity.Category;
import com.example.Inventory_And_Order_Management_API.Entity.Product;
import com.example.Inventory_And_Order_Management_API.Enums.ProductStatus;
import com.example.Inventory_And_Order_Management_API.Exception.ResourceNotFoundException;
import com.example.Inventory_And_Order_Management_API.Repository.CategoryRepository;
import com.example.Inventory_And_Order_Management_API.Repository.ProductRepository;
import com.example.Inventory_And_Order_Management_API.Services.ProductService;
import com.example.Inventory_And_Order_Management_API.Util.MapperUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponse create(ProductRequest request) {

        List<Category> categories = categoryRepository.findAllById(request.categoryIds());

        Product product = new Product();
        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setStatus(ProductStatus.valueOf(request.status()));
        product.setCategories(categories);

        Product saved = productRepository.save(product);

        return mapToResponse(saved);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found"));

        product.setName(request.name());
        product.setDescription(request.description());
        product.setPrice(request.price());
        product.setStock(request.stock());
        product.setStatus(ProductStatus.valueOf(request.status()));

        Product updated = productRepository.save(product);

        return mapToResponse(updated);
    }

    @Override
    public Page<ProductResponse> getProducts(
            String category,
            String search,
            Integer lowStock,
            Pageable pageable
    ) {

        Page<Product> products;

        if (category != null) {
            products = productRepository.findByCategories_Name(category, pageable);
        } else if (search != null) {
            products = productRepository.findByNameContainingIgnoreCase(search, pageable);
        } else if (lowStock != null) {
            products = productRepository.findByStockLessThan(lowStock, pageable);
        } else {
            products = productRepository.findAll(pageable);
        }

        return products.map(MapperUtil::toProductResponse);
    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

    private ProductResponse mapToResponse(Product product) {
        List<String> categoryNames = product.getCategories()
                .stream()
                .map(Category::getName)
                .collect(Collectors.toList());

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
}
